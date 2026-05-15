package org.sopt.kakaotalk.domain.folder.repository;

import java.util.List;
import org.sopt.kakaotalk.domain.folder.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {

  List<Folder> findAllByOrderByFolderIdAsc();
}
