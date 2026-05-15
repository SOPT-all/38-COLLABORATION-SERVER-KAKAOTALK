package org.sopt.kakaotalk.domain.folder.service;

import lombok.RequiredArgsConstructor;
import org.sopt.kakaotalk.domain.folder.dto.response.FolderListResponse;
import org.sopt.kakaotalk.domain.folder.repository.FolderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FolderService {

  private final FolderRepository folderRepository;

  public FolderListResponse getFolders() {
    return FolderListResponse.from(folderRepository.findAllByOrderByFolderIdAsc());
  }
}
