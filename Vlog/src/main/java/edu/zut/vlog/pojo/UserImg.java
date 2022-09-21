package edu.zut.vlog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/10
 * Time: 9:50
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserImg {

    private Integer id;
    private Integer albumId;
    private String imgUrl;


}
