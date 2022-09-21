package edu.zut.vlog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/12
 * Time: 18:04
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImgJson {

    private Integer code;
    private String msg;
    private Map<String,String> data;

}
