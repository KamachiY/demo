package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Item;
import com.example.demo.form.Form;
import com.example.demo.service.SampleService;

@RequestMapping("/sample")
@Controller
public class SampleController {
	
	@Autowired
	SampleService service;
	
	/**
	 * 初期化処理
	 */
	@ModelAttribute
	public Form setUpForm() {
		Form form = new Form();
		return form;
	}

	/*:
	 * 一覧画面表示
	 */
	@GetMapping
	public String ShowList(Form form, Model model) {

		form.setIsNew(true); // 新規登録
		Iterable<Item> entity = service.SelectAll(); //全件取得
		model.addAttribute("list", entity);
		return "show";
	}
	
	/**
	 * 登録
	 */
	@PostMapping("/insert")
	public String insert(@Validated Form form, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		/**
		 * FormからEntityに詰め替える
		 */
		Item entity = new Item();
		entity.setId(form.getId());
		entity.setName(form.getName());
		entity.setPrice(form.getPrice());
		/**
		 * 入力チェック
		 */
		if( !result.hasErrors()) {
			service.InsertData(entity);
			redirectAttributes.addFlashAttribute("complete", "登録が完了しました。");
			return "redirect:/sample";
		} else {
			// エラーが起きたら一覧画面へ
			return ShowList(form, model);
		}		
	}
	
	/**
	 * 更新
	 */
	// 更新画面表示
	@GetMapping("/{id}")
	public String ShowUpdate(Form form, @PathVariable Integer id, Model model) {
		// 表示する行取得
		Optional<Item> opt = service.SelectOneById(id);
		
		// formに入れ直す
		Optional<Form> formOpt = opt.map(t -> makeForm(t));
		
		// formがnullでなければ中身を取り出す
		if(formOpt.isPresent()) {
			form = formOpt.get();
		}
		makeUpdateModel(form, model);
		return "show";
		
	}

	// 更新用Model作成
	private void makeUpdateModel(Form form, Model model) {
		model.addAttribute("id", form.getId());
		form.setIsNew(false);
		model.addAttribute("form", form);
	}
	
	// idをキーにしてデータ更新
	@PostMapping("/update")
	public String update(@Validated Form form, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		Item entity = new Item();
//		entity.setId(form.getId());
		entity.setName(form.getName());
		entity.setPrice(form.getPrice());
		if(!result.hasErrors()) {
			service.UpdateData(entity);
			redirectAttributes.addFlashAttribute("complete", "更新が完了しました。");
			return "redirect:/sample/" + entity.getId();
		} else {
			// エラーが起きたら更新用モデルを初期化して一覧画面へ
			makeUpdateModel(form, model);
			return "show";
		}
	}

	// entityからformへ値を代入
	private Form makeForm(Item entity) {
		Form form = new Form();
//		form.setId(entity.getId());
		form.setName(entity.getName());
		form.setPrice(entity.getPrice());
		form.setIsNew(false);
		return form;
	}
	
	/**
	 * 削除
	 */
	@PostMapping("/delete")
	public String delete(@RequestParam("id") String id, Model model, RedirectAttributes redirectAttributes) {
		service.DeleteDataById(Integer.parseInt(id));
		redirectAttributes.addFlashAttribute("complete", "削除が完了しました。");
		return "redirect:/sample";
	}
	
}
