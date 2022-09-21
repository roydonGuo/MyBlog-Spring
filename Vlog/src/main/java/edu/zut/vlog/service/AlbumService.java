package edu.zut.vlog.service;

import com.github.pagehelper.PageInfo;
import edu.zut.vlog.pojo.Album;

import java.util.List;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/11
 * Time: 15:06
 **/
public interface AlbumService {

    //查询全部用户的全部相册
    List<Album> queryAllAlbum();

    List<Album> queryAlbumList();

    int countAlbumList();
    PageInfo<Album> queryAlbumPageList(Integer pageNum, Integer pageSize);

    PageInfo<Album> queryMyAlbums(Integer userId,Integer pageNum, Integer pageSize);

    //查询当前用户的相册
    List<Album> queryAlbumsByUserId(Integer userId);

    //查询用户相册,仅仅查询相册一张表
    //到时作为预览在主页面显示
    List<Album> queryAllAlbumByNowUserId(Integer userId);

    List<Album> queryAlbumAndUser();

    int addAlbum(String title, String imgDescribe, Integer userId, String coverImgUrl);
}
