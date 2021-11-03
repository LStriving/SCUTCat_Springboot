package com.scutcat.demo.uitls;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult {
    boolean status;
    String message;
    Object data;
}