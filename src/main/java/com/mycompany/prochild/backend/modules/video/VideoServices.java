/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.prochild.backend.modules.video;

import com.mycompany.prochild.backend.models.Video;
import java.util.List;

/**
 *
 * @author jcmol
 */
public class VideoServices {
    private VideoRepository video = new VideoRepository();
    
    public List<Video> findAllVideos() {
        return video.findAllVideos();
    }
    
    public Video findVideoById(int videoId) {
        return video.findVideoById(videoId);
    }
    
    public int insertVideo(Video dir) {
        return video.insertVideo(dir);
    }
    
    public int updateVideo(Video dir) {
        return video.updateVideo(dir);
    }
    
    public int removeVideo(int videoId) {
        return video.removeVideo(videoId);
    }
}
