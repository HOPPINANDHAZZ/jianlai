package com.hoppinzq.service.dao;

import com.hoppinzq.service.bean.*;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlogDao {

    @Select("SELECT * FROM blog_class ORDER BY parent_id")
    List<Map> queryBlogClass();

    @Insert("<script>" +
            "replace into blog" +
            "<trim prefix='(' suffix=')' suffixOverrides=','>" +
            "<if test=\"id != null\">id,</if>" +
            "<if test=\"title != null and title != ''\">title,</if>" +
            "<if test=\"description != null and description != ''\">description,</if>" +
            "<if test=\"buildType != null\">build_type,</if>" +
            "<if test=\"csdnLink != null and csdnLink != ''\">csdn_link,</if>" +
            "<if test=\"text != null and text != ''\">text,</if>" +
            "<if test=\"blogLike != null\">blog_like,</if>" +
            "<if test=\"star != null\">star,</if>" +
            "<if test=\"collect != null\">collect,</if>" +
            "<if test=\"author != null and author != ''\">author,</if>" +
            "<if test=\"authorName != null and authorName != ''\">author_name,</if>" +
            "<if test=\"createTime != null\">create_time,</if>" +
            "<if test=\"updateTime != null\">update_time,</if>" +
            "<if test=\"filePath != null and filePath != ''\">file_path,</if>" +
            "<if test=\"fileId != null and fileId != ''\">file_id,</if>" +
            "<if test=\"isComment != null\">is_comment,</if>" +
            "<if test=\"blogClass != null and blogClass != ''\">blog_class,</if>" +
            "<if test=\"isCreateSelf != null\">is_create_self,</if>" +
            "<if test=\"musicFile != null and musicFile != ''\">music_file,</if>" +
            "<if test=\"image != null and image != ''\">image,</if>" +
            "<if test=\"html != null and html != ''\">html,</if>" +
            "<if test=\"copyLink != null and copyLink != ''\">copy_link,</if>" +
            "<if test=\"type != null\">type,</if>" +
            "<if test=\"show != null\">show_num,</if>" +
            "<if test=\"blogClassName != null and blogClassName != ''\">blog_class_name,</if>" +
            "</trim>" +
            "<trim prefix='values (' suffix=')' suffixOverrides=','>" +
            "   <if test=\"id != null\">#{id},</if>" +
            "   <if test=\"title != null and title != ''\">#{title},</if>" +
            "   <if test=\"description != null and description != ''\">#{description},</if>" +
            "   <if test=\"buildType != null\">#{buildType},</if>" +
            "   <if test=\"csdnLink != null and csdnLink != ''\">#{csdnLink},</if>" +
            "   <if test=\"text != null and text != ''\">#{text},</if>" +
            "   <if test=\"blogLike != null\">#{blogLike},</if>" +
            "   <if test=\"star != null\">#{star},</if>" +
            "   <if test=\"collect != null\">#{collect},</if>" +
            "   <if test=\"author != null and author != ''\">#{author},</if>" +
            "   <if test=\"authorName != null and authorName != ''\">#{authorName},</if>" +
            "   <if test=\"createTime != null\">#{createTime},</if>" +
            "   <if test=\"updateTime != null\">#{updateTime},</if>" +
            "   <if test=\"filePath != null and filePath != ''\">#{filePath},</if>" +
            "   <if test=\"fileId != null and fileId != ''\">#{fileId},</if>" +
            "   <if test=\"isComment != null\">#{isComment},</if>" +
            "   <if test=\"blogClass != null and blogClass != ''\">#{blogClass},</if>" +
            "   <if test=\"isCreateSelf != null\">#{isCreateSelf},</if>" +
            "   <if test=\"musicFile != null and musicFile != ''\">#{musicFile},</if>" +
            "   <if test=\"image != null and image != ''\">#{image},</if>" +
            "   <if test=\"html != null and html != ''\">#{html},</if>" +
            "   <if test=\"copyLink != null and copyLink != ''\">#{copyLink},</if>" +
            "   <if test=\"type != null\">#{type},</if>" +
            "   <if test=\"show != null\">#{show},</if>" +
            "   <if test=\"blogClassName != null and blogClassName != ''\">#{blogClassName},</if>" +
            "</trim>" +
            "</script>")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insertOrUpdateBlog(Blog entity);

    List<Blog> queryBlog(@Param(value = "blog") BlogVo blogVo);
    int countBlog(@Param(value = "blog") BlogVo blogVo);

    void updateBlog(Blog blog);

    @Delete("delete from blog where id = #{id}")
    void deleteBlog(long id);

    @Insert("insert into csdn_error_link(url,user) values(#{url},#{userId})")
    void insertErrorLinkCSDN(String url,String userId);

    @Insert("<script>" +
            " insert into blog_class_mid (blog_id, class_id,auth_id) " +
            "    VALUES" +
            "    <foreach collection='list' item='blog' separator=','>" +
            "        ( #{blog.blogId}, #{blog.classId},#{blog.authId})" +
            "    </foreach>" +
            "</script>")
    void insertBlogMidClasses(List<BlogMidClass> blogClasses);

    @Insert("<script>" +
            " insert into blog_class_mid (blog_id, class_id,auth_id) " +
            "    VALUES" +
            "    <foreach collection='classes' item='blogClass' separator=','>" +
            "        ( #{blog_id}, #{blogClass},#{authId})" +
            "    </foreach>" +
            "</script>")
    void insertBlogMidClassesById(@Param("classes")List<String> blogClasses, long blog_id,long authId);

    @Insert("<script>" +
            " insert into blog_class (id,parent_id, name,author) " +
            "    VALUES" +
            "    <foreach collection='blogClasses' item='blogClass' separator=','>" +
            "        (#{blogClass.id},#{blogClass.parent_id}, #{blogClass.name},#{blogClass.author})" +
            "    </foreach>" +
            "</script>")
    void insertBlogClasses(@Param("blogClasses") List<BlogClass> blogClasses);

    @Delete("<script>" +
            "delete from blog_class_mid where blog_id in " +
            "   <foreach collection='list' item='blog' open='(' separator=',' close=')'>" +
            "       #{blog.blogId}" +
            "   </foreach>" +
            "</script>")
    void deleteBlogClasses(List<BlogMidClass> blogClasses);

    @Delete("delete from blog_class_mid where blog_id = #{blog_id}")
    void deleteBlogClassesById(long blog_id);

    List<Comment> queryComment(@Param(value = "comment") CommentVo commentVo);
    int countComment(@Param(value = "comment") CommentVo commentVo);

    void updateComment(@Param(value = "comment") Comment comment);

    /**
     * 浏览量+1
     * 允许容错，允许show因为高并发导致更新错误
     * @param blog_id
     */
    @Update("update blog set show_num=show_num+1 where id = #{blog_id}")
    void updateShow(long blog_id);

    @Insert("<script>" +
            " replace into search_key (searchfull,search,author) " +
            "    VALUES" +
            "    <foreach collection='list' item='searchKey' separator=','>" +
            "        ( #{searchKey.searchFull}, #{searchKey.searchFc},#{searchKey.author})" +
            "    </foreach>" +
            "</script>")
    void insertSearchKeys(List<SearchKey> searchKey);

    List<Map> queryHotKey(@Param(value = "params") Map map);

    @Select("<script>" +
            "SELECT bc.id,bc.parent_id,bc.name,bc.description,bc.nickname,bc.create_time,bc.update_time,<if test=\"auth_id != 0\"> bc.author, </if>bmc.class_id,bmc.class_count FROM blog_class bc LEFT JOIN  " +
            "(SELECT class_id,COUNT(class_id) AS class_count " +
            "FROM blog_class_mid WHERE 1=1 <if test=\"auth_id != 0\">and auth_id = #{auth_id}</if> GROUP BY class_id) bmc ON bc.id = bmc.class_id " +
            "ORDER BY class_count DESC LIMIT #{limit}" +
            "</script>")
    List<Map> queryUserClassCount(Long auth_id,int limit);

    Map getAuthorById(Long authorId);

    @Select("select count(1) from blog where author = #{authorId}")
    int getAuthorAllBlogCount(Long authorId);

    @Select("select count(1) from blog where type = 1 and author = #{authorId}")
    int getBlogCgCount(Long authorId);

    @Select("select title,description,blog_class_name,author from blog where id = #{blogId}")
    Map getBlogById(Long blogId);
}
