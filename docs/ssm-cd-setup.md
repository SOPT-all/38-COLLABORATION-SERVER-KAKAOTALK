# SSM 기반 CD 전환 가이드

## 목적

- GitHub Actions에서 EC2로 직접 SSH 접속하지 않고 AWS Systems Manager Run Command로 배포한다.
- 운영 서버의 SSH 인바운드를 GitHub Actions용으로 넓히지 않는다.

## AWS 사전 준비

### 1. EC2 인스턴스 준비

- EC2 인스턴스에 Systems Manager용 IAM Role을 연결한다.
- 해당 Role에는 `AmazonSSMManagedInstanceCore` 정책을 붙인다.
- Systems Manager 콘솔의 `Managed nodes`에서 대상 인스턴스가 온라인 상태로 보여야 한다.

### 2. GitHub Actions용 IAM Role 준비

- AWS IAM에 GitHub OIDC Provider를 등록한다.
- 배포용 IAM Role의 trust policy는 `main` 브랜치만 assume할 수 있도록 제한한다.
- 배포용 IAM Role에는 최소한 아래 권한이 필요하다.

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": [
        "ssm:SendCommand"
      ],
      "Resource": [
        "arn:aws:ssm:<AWS_REGION>::document/AWS-RunShellScript",
        "arn:aws:ec2:<AWS_REGION>:<AWS_ACCOUNT_ID>:instance/<EC2_INSTANCE_ID>"
      ]
    },
    {
      "Effect": "Allow",
      "Action": [
        "ssm:GetCommandInvocation"
      ],
      "Resource": "*"
    }
  ]
}
```

### 3. GitHub Actions Secret 준비

저장소에는 아래 Secret이 필요하다.

- `AWS_ROLE_ARN`
- `AWS_REGION`
- `EC2_INSTANCE_ID`
- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`
- `DDL_AUTO`

기존 SSH 배포에서 쓰던 아래 Secret은 SSM 전환 후 더 이상 사용하지 않는다.

- `EC2_HOST`
- `EC2_USER`
- `EC2_SSH_KEY`

## 배포 흐름

1. `main` 브랜치에 push가 발생한다.
2. GitHub Actions가 JAR과 Docker 이미지를 빌드하고 GHCR에 push한다.
3. GitHub Actions가 OIDC로 AWS Role을 assume한다.
4. `docker-compose.prod.yml`과 임시 `.deploy.env` 내용을 SSM Run Command로 EC2에 전달한다.
5. EC2가 최신 이미지를 pull하고 컨테이너를 재기동한다.
6. 배포가 끝나면 임시 `.deploy.env` 파일을 삭제한다.

## 운영 메모

- 이 방식은 GitHub Actions runner가 EC2의 `22/tcp`로 직접 접속하지 않는다.
- 현재 1차 전환안은 기존 GitHub Secrets 운용을 유지하기 위해 배포용 환경값을 SSM 명령으로 전달한다.
- 장기적으로는 `DB_PASSWORD` 같은 민감값을 AWS Systems Manager Parameter Store 또는 AWS Secrets Manager로 옮기는 편이 더 안전하다.
