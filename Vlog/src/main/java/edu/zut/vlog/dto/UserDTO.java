package edu.zut.vlog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/5/25
 * Time: 21:00
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String petName;
    private String headPortrait;

}
