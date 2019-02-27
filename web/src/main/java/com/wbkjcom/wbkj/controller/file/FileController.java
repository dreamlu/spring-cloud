package com.wbkjcom.wbkj.controller.file;

import com.wbkjcom.commons.lib.Lib;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping(value = "/files")
public class FileController {

	// 文件上传路径
	private static final String UploadPath = "static/file/";

	@PostMapping("/upload")
	@ResponseBody
	public Object upload(@RequestParam("file") MultipartFile file, @RequestParam(required = false) String name) {

		if (file.isEmpty()) {
			return Lib.GetMapData(Lib.CodeFile, "文件不能为空");
		}

		String filename = null;
		Path   path     = null;
		try {
			// 文件字节数据
			byte[] bytes = file.getBytes();
			filename = file.getOriginalFilename();
			String rootPath = ResourceUtils.getURL("classpath:public").getPath() + "/" + UploadPath;
			//System.out.println("文件根路径: " + rootPath);
			File upload = new File(rootPath);
			if(!upload.exists()) upload.mkdirs();

			// 获取文件的后缀名
			String suffixName = filename.substring(filename.lastIndexOf("."));

			if(name != null && name != "") {
				filename = name + suffixName;
			}else {
				filename = UUID.randomUUID() + suffixName;
			}

			path = Paths.get(rootPath + filename);


			Files.write(path, bytes);
			//System.out.println("文件路径: " + path.toString());

		} catch (IOException e) {
			e.printStackTrace();
			return Lib.GetMapData(Lib.CodeFile, "文件上传错误", e.toString());
		}
		Map<String, Object> map = new HashMap<>();
		map.put("path", UploadPath + filename);

		return Lib.GetMapData(Lib.CodeFile, Lib.MsgFile, map);
	}
}
