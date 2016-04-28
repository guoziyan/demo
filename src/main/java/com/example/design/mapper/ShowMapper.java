package com.example.design.mapper;

import com.example.design.model.Show;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 作品持久化接口. Created by lxh on 4/14/16.
 */
@Repository("articleMapper")
public interface ShowMapper {
  /**
   * add one cookingShow to a table
   */
  @Insert("INSERT INTO show(showIntro, showPicture, showDate, authorId, cookingId) " +
          "VALUES(#{showIntro}, #{showPicture}, #{showDate}, #{authorId}, #{cookingId})")
  int addShow(Show show);

  @Insert("INSERT INTO show(cookingId) VALUES (#{cookingId} WHERE showId = #{showId})")
  int addShowToCooking(Show show);

  /**
   * Update show information
   */
  @Update("UPDATE show SET showIntro = #{showIntro}, showPicture = #{showPicture} " +
          "WHERE showId = #{showId} AND state = 0")
  int updateShow(Show show);

  /**
   * mark show'state as "deleted"
   */
  @Update("UPDATE show SET state = 1 WHERE showId = #{showId} AND state = 0")
  int markShowDelete(long showId);

  /**
   * select one show by it's Id
   *
   * @return Show
   */
  @Select("SELECT * FROM show WHERE showId = #{showId}")
  Show findShowById(long showId);

  /**
   * select one user's all show
   *
   * @return List<Show>
   */
  @Select("SELECT * FROM show WHERE authorId = #{authorId} AND state = 0 ")
  List<Show> findAllShowByUserId(@Param("authorId") long userId);

  /**
   * select one cooking's all show
   *
   * @return List<Show>
   */
  @Select("SELECT * FROM show WHERE cookingId = #{cookingId} AND state = 0 ")
  List<Show> findAllShowByCookingId(long cookingId);

  /**
   * select all show
   *
   * @return List<Show>
   */
  @Select("SELECT * FROM show")
  List<Show> all();
}
