package com.example.GReads_Clone.ReadingLists;

import com.example.GReads_Clone.enums.ReadingListType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReadingListRepository extends JpaRepository<ReadingList,Long> {

   ReadingList findListByUser(Long userId);
   boolean existsByUserIdAndListType(Long userId, ReadingListType listType);
}
