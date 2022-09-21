package edu.zut.vlog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/10
 * Time: 9:46
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    private Integer id;
    private String title;//相册标题
    private String albumDescribe;//相册描述albumDescribe

    //private   createTime;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate createTime;//创建时间
    private Integer userId;
    private String coverImgUrl;//相册封面

    private Users user;

    private List<UserImg> userImgList;//一个相册包含多个图片url


}
