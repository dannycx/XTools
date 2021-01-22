package com.danny.xtools.utils

import android.app.Activity
import android.net.Uri
import android.widget.VideoView

/**
 * 视频播放
 *
 * @author danny
 * @since 2020-11-06
 */
object XVideoUtil {

    fun setUri(activity: Activity, rawId: Int, video: VideoView) {
        video.setVideoURI(Uri.parse("android.resource://${activity.packageName}/${rawId}"))
    }

    fun play(video: VideoView) {
        if (!video.isPlaying) {
            video.start()
        }
    }

    fun pause(video: VideoView) {
        if (video.isPlaying) {
            video.pause()
        }
    }

    fun replay(video: VideoView) {
        if (video.isPlaying) {
            video.resume()
        }
    }

    fun destroy(video: VideoView) {
        video.suspend()
    }

}
