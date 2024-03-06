package com.example.demo.form;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Form {
//	@NotNull
//    @Min(0)                 // 0以上の値を入力可能
	private Integer id;
	
	@NotBlank
	@Length(min=1, max=100) // 1～100文字の範囲で入力可能
	private String name;
	
	@NotNull
	@Min(0)  // 0以上の値を入力可能
	private Integer price;
	
	/* 「登録」か「更新」判定用 */
	private Boolean isNew;
}
