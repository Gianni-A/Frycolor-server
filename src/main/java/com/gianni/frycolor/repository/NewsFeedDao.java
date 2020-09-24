package com.gianni.frycolor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gianni.frycolor.entities.NewsFeed;
import com.gianni.frycolor.entities.User;
import com.gianni.frycolor.model.PostModel;

@Repository
public interface NewsFeedDao extends JpaRepository<NewsFeed, Integer> {
	
	@Query(value = "SELECT count(1) FROM news WHERE nw_id = :nwId AND nw_status = 1", nativeQuery = true)
	int isPostActive(@Param("nwId") int nwId);
	
	@Query(value = "SELECT postList FROM NewsFeed postList WHERE postList.usId = :userId", nativeQuery = false)
	List<NewsFeed> getNewsFeedList(@Param("userId") int userId);

	@Query(value = "SELECT" + 
			"	new com.gianni.frycolor.model.PostModel(n.nwId,uc.usComComment,um.usMdPath,nr.nwrId,ui.usInfPath_image," +
		    "   CONCAT(ui.usInfName, ' ', ui.usInfLastname))" +
			"	FROM" + 
			"	NewsFeed n" + 
			"	INNER JOIN UserFriends uf ON n.usId = uf.frdUsIdUf OR n.usId = :userId" + 
			"   INNER JOIN User u ON n.usId = u.usId" +
			"   INNER JOIN UserInformation ui ON u.usInfId = ui.usInfId" +
			"	LEFT JOIN UserComments uc ON n.usCommentId = uc.usComId" + 
			"	LEFT JOIN UserMedia um ON n.usMdId = um.usMdId" + 
			"   LEFT JOIN NewsReaction nr ON n.nwId = nr.nwId AND nr.usId = :userId" +
			"	WHERE (uf.frdUsId = :userId OR n.usId = :userId) AND n.nwStatus = 1" +
			"   GROUP BY uf.frdUsId, n.nwId, ui.usInfId, uc.usComId, um.usMdId, nr.nwrId" +
			"   ORDER BY n.nwTsUpdated", nativeQuery = false)
	List<PostModel> getAllListPost(@Param("userId") User userId);
}
