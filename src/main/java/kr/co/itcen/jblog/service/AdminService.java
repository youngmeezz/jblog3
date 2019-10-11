package kr.co.itcen.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.exception.FileuploadException;
import kr.co.itcen.jblog.repository.BlogDao;
import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.repository.PostDao;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;

@Service
public class AdminService {

	private static final String SAVE_PATH = "/uploads";
	private static final String URL_PREFIX = "/images";

	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private PostDao postDao;

	
	
	// 카테고리는 따로 해줌 CategoryService에서

	// 글쓰기 한 것 조회하기
	public List<PostVo> getList(Long categoryNo) {
		return postDao.getList(categoryNo);
	}

	// 글쓰기 창 작성하기
	public void write(PostVo postvo) {
		// TODO Auto-generated method stub
		postDao.write(postvo);
	}

	// 파일 업로드하기
	// 블로그 title, logo 변경하기
	public Boolean restore(MultipartFile multipartFile, BlogVo vo) {
		String url = "";

		try {

			if (multipartFile == null) {
				return false;
			}

			String originalFilename = multipartFile.getOriginalFilename();
			String saveFileName = generateSaveFilename(
					originalFilename.substring(originalFilename.lastIndexOf('.') + 1));
			long fileSize = multipartFile.getSize();

			byte[] fileData = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFileName);
			os.write(fileData);
			os.close();

			url = URL_PREFIX + "/" + saveFileName;
		} catch (IOException e) {
			throw new FileuploadException();
		}
		// 이url을
		vo.setLogo(url);
		return blogDao.update(vo);
	}

	private String generateSaveFilename(String extName) {

		String filename = "";

		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);

		return filename;
	}

	public List<CategoryVo> select(CategoryVo categoryvo) {
		return categoryDao.getList(categoryvo);
	}

}
