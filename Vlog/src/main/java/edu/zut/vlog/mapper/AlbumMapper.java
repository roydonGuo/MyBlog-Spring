package edu.zut.vlog.mapper;

import edu.zut.vlog.pojo.Album;
import edu.zut.vlog.pojo.UserImg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/10
 * Time: 20:01
 **/
@Mapper
public interface AlbumMapper {
    //查询所有人的所有相册
    List<Album> queryAllAlbum();

    List<Album> queryAlbumList();//只查询相册

    List<Album> queryAlbumAndUser();
    int countAllAlbum();

    /**
     *
     * @param albumId albumId
     * @return userId
     */
    int queryUserIdByAlbumId(Integer albumId);


    /**
     * //查询个人相册//用户，照片一并封装
     * @param userId userId
     * @return List<Album>
     */
    List<Album> queryAlbumByUserId(Integer userId);

    /**
     * 根据相册id封装单个实体
     * @param albumId 相册id
     * @return album
     */
    Album queryTitleById(Integer albumId);

    /**
     * //增加相册,暂时增加下面四项
     * @param title
     * @param imgDescribe
     * @param userId
     * @param coverImgUrl
     * @return
     */
    int addAlbum(String title, String imgDescribe,Integer userId,String coverImgUrl);

    /**
     * 待考究，多表删除，有外键约束的多表删除
     * 1.先删除从表再删主表（临时办法）
     * @param albumId
     * @return
     */
    int deleteImgs(Integer albumId);//先删除相册中的全部图片
    int deleteAlbum(Integer albumId);//根据相册的id删除相册，

    String getAlbumCoverge(Integer albumId);

    List<String> queryImgsUrl(Integer albumId);

    String queryImgUrl(Integer id);//根据img的id拿到一个图片
    //用户相册查询根据当前用户登录的id,仅仅查询相册封装
    List<Album> queryUserAlbumByUserId(Integer userId);

    //对相册内的图片进行查询
    List<UserImg> queryImgByAlbumId(Integer albumId);

}
