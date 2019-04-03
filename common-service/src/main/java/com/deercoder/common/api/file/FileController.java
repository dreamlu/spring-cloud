package com.deercoder.common.api.file;

import com.deercoder.commons.lib.Lib;
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
@RequestMapping(value = "/file")
public class FileController {

	// 项目名
//	@Value("${spring.application.name}")
//	private String projectName;

	// 文件上传路径
	private static final String UploadPath = "common-service/static/file/";

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
			// jar 包路径问题
			//获取根目录
			File rootFile = new File(ResourceUtils.getURL("file:").getPath());
			if(!rootFile.exists()) rootFile = new File("");
			//System.out.println("rootFile:"+rootFile.getAbsolutePath());

			File upload = new File(rootFile.getAbsolutePath(), UploadPath);
			if(!upload.exists()) upload.mkdirs();
			//System.out.println("upload url:"+upload.getAbsolutePath());

			String            rootPath    = upload.getAbsolutePath();// + "/" + UploadPath;
			//System.out.println("文件根路径: " + rootPath);
			//upload = new File(rootPath);
			//if (!upload.exists()) upload.mkdirs();

			// 获取文件的后缀名
			String suffixName = filename.substring(filename.lastIndexOf("."));

			if (name != null && name != "") {
				filename = name + suffixName;
			} else {
				filename = UUID.randomUUID() + suffixName;
			}

			path = Paths.get(rootPath + "/" + filename);

			Files.write(path, bytes);
			System.out.println("文件根目录==>rootFile: " + rootFile.getAbsolutePath());
			System.out.println("文件目录==>rootPath: " + rootPath);
			System.out.println("文件路径==>: " + path.toString());

		} catch (IOException e) {
			e.printStackTrace();
			return Lib.GetMapData(Lib.CodeFile, "文件上传错误", e.toString());
		} catch (Exception e) {
			return Lib.GetMapData(Lib.CodeError, e.toString());
		}
		Map<String, Object> map = new HashMap<>();
		map.put("path", UploadPath + filename);

		return Lib.GetMapData(Lib.CodeFile, Lib.MsgFile, map);
	}
}
