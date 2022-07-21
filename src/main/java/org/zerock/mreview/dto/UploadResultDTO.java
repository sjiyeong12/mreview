package org.zerock.mreview.dto;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadResultDTO implements Serializable {
    private String fileName; // 파일이름
    private String uuid; // 고유번호
    private String folderPath; // 폴더 경로

    public String getImageURL() {
        try {
            return URLEncoder.encode(folderPath + "/" + uuid + "_" + fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getThumbnailURL() { // JSON으로 전달되는 UploadResultDTO에 섬네일의 링크를 처리하기 위한 메서드 추가
        try {
            return URLEncoder.encode(folderPath + "/s_" + uuid + "_" + fileName, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

}
