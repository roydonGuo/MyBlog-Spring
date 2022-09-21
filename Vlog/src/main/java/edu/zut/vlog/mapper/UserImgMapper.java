package edu.zut.vlog.mapper;

import edu.zut.vlog.pojo.UserImg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/13
 * Time: 9:51
 **/
@Mapper
public interface UserImgMapper {
    List<UserImg> queryAllUserImgs(Integer albumId);

    int addUserImg(Integer albumId,String imgUrl);

    int deleteUserImgByImgId(Integer imgId);

}
