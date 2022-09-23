package com.example.filedata;

import com.example.filedata.files.entities.Image;
import com.example.filedata.files.repos.ImageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.Optional;

@SpringBootTest
class FiledataApplicationTests {

	@Autowired
	ImageRepository repository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testImageSave() throws IOException {

		Image image = new Image();
		image.setId(1);
		image.setName("cat.jpg");

		File file = new File("/home/robin/Pictures/cat.jpeg");
		byte[] fileContent = new byte[(int) file.length()];
		FileInputStream inputStream = new FileInputStream(file);
		inputStream.read(fileContent);

		image.setData(fileContent);
		repository.save(image);
		inputStream.close();
	}

	@Test
	public void testReadImage() {

		Image image = repository.findById(1L).get();
		File file = new File("/home/robin/Pictures/downloads/" + image.getName().replace(".jpg", "1.jpg"));
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file);
			outputStream.write(image.getData());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}


