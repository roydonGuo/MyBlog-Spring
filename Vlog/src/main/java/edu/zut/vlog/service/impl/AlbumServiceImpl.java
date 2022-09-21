package edu.zut.vlog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.zut.vlog.mapper.AlbumMapper;
import edu.zut.vlog.pojo.Album;
import edu.zut.vlog.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/11
 * Time: 15:11
 **/
@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumMapper albumMapper;
    private HttpServletRequest request;

    @Override
    public List<Album> queryAllAlbum() {
        return albumMapper.queryAllAlbum();
    }

    @Override
    public List<Album> queryAlbumList() {
        return albumMapper.queryAlbumList();
    }

    @Override
    public int countAlbumList() {
        return albumMapper.countAllAlbum();
    }

    @Override
    public List<Album> queryAlbumAndUser() {
        return albumMapper.queryAlbumAndUser();
    }

    @Override
    public PageInfo<Album> queryAlbumPageList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Album> list = albumMapper.queryAlbumAndUser();
        PageInfo<Album> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<Album> queryMyAlbums(Integer userId,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Album> list = albumMapper.queryUserAlbumByUserId(userId);
        PageInfo<Album> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<Album> queryAlbumsByUserId(Integer userId) {
        return albumMapper.queryAlbumByUserId(userId);
    }

    @Override
    public List<Album> queryAllAlbumByNowUserId(Integer userId) {
        return albumMapper.queryUserAlbumByUserId(userId);
    }

    @Override
    public int addAlbum(String title, String imgDescribe, Integer userId, String coverImgUrl) {
        return albumMapper.addAlbum(title, imgDescribe, userId, coverImgUrl);
    }
}
