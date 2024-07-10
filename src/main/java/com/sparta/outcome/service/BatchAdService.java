//package com.sparta.outcome.service;
//
//import com.sparta.outcome.dto.BatchAdRequestDto;
//import com.sparta.outcome.entity.Ad;
//import com.sparta.outcome.entity.Video;
//import com.sparta.outcome.entity.VideoAd;
//import com.sparta.outcome.repository.AdRepository;
//import com.sparta.outcome.repository.AdViewRepository;
//import com.sparta.outcome.repository.VideoAdRepository;
//import com.sparta.outcome.repository.VideoRepository;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class BatchAdService {
//
//    public final VideoAdRepository videoAdRepository;
//    private final AdViewRepository adViewRepository;
//    private final VideoRepository videoRepository;
//    private final AdRepository adRepository;
//
//    @Transactional
//    public String countAdViewsByVideoAdAndDate(BatchAdRequestDto batchAdRequestDto) {
//
//        // Video와 Ad 객체를 각각 가져옴
//        Video video = videoRepository.findById(batchAdRequestDto.getVidId())
//                .orElseThrow(() -> new RuntimeException("Video not found with id " + batchAdRequestDto.getVidId()));
//        Ad ad = adRepository.findById(batchAdRequestDto.getAdId())
//                .orElseThrow(() -> new RuntimeException("Ad not found with id " + batchAdRequestDto.getAdId()));
//
//        // video와 ad로 VideoAd 엔티티들을 찾음
//        List<VideoAd> videoAds = videoAdRepository.findVideoAdsByVideoAndAd(video, ad);
//
//        // videoAds 리스트와 date로 AdView 엔티티들을 카운트
//        int adViewCount = (int) adViewRepository.countByVideoAdsAndDate(videoAds, batchAdRequestDto.getDate());
//
//        // get 0 은 video_ad (하나의동영상에 대한 하나의광고)의 total_ad_view (누적합)는 같이올라감.
//        int lastTotalView = (int) videoAds.get(0).getTotalAdView();
//
//        double revenue = 0;
//        if (lastTotalView < 100000) {
//            int firstHalfView = 99999 - lastTotalView; // 10만까지 남은 뷰
//            if (adViewCount <= firstHalfView) {
//                revenue = adViewCount * 10;
//            } else {
//                revenue += firstHalfView * 10; // * 1원
//                int secondHalfView = adViewCount - firstHalfView; //10만 넘김
//                revenue += secondHalfView * 12;
//            }
//        } else if (lastTotalView < 500000) {
//            int firstHalfView = 499999 - lastTotalView; // 50만 미만
//            if (adViewCount <= firstHalfView) {
//                revenue = adViewCount * 12;
//            } else {
//                revenue += firstHalfView * 12;
//                int secondHalfView = adViewCount - firstHalfView; //50만 넘김
//                revenue += secondHalfView * 15;
//            }
//        } else if (lastTotalView < 1000000) {
//            int firstHalfView = 999999 - lastTotalView; // 100만 미만
//            if (adViewCount <= firstHalfView) {
//                revenue = adViewCount * 15;
//            } else {
//                revenue += firstHalfView * 15;
//                int secondHalfView = adViewCount - firstHalfView; //100만 넘김
//                revenue += secondHalfView * 20;
//            }
//        } else {
//            revenue = adViewCount * 20;
//        }
//
//
//        // 각 VideoAd 엔티티의 totalAdView를 업데이트
//        videoAds.forEach(videoAd -> {
//            videoAd.setTotalAdView(videoAd.getTotalAdView() + adViewCount);
//            videoAdRepository.save(videoAd);
//        });
//
//
//
//
//        return batchAdRequestDto.getVidId()+ "번 영상의 "+ batchAdRequestDto.getAdId() +"광고의 총 조회수"+adViewCount+
//                " / 정산금액" + revenue;
//
//
//    }
//}
