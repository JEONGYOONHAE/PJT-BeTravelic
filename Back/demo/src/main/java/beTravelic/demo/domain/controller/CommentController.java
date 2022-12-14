package beTravelic.demo.domain.controller;


import beTravelic.demo.domain.dto.CommentResDto;
import beTravelic.demo.domain.dto.CommentSaveRequestDto;
import beTravelic.demo.domain.dto.CommentUpdateRequestDto;
import beTravelic.demo.domain.dto.ReviewResDto;
import beTravelic.demo.domain.entity.Comment;
import beTravelic.demo.domain.service.CommentService;
import beTravelic.demo.global.common.CommonResponse;
import beTravelic.demo.global.util.jwt.JwtProvider;
import io.jsonwebtoken.Jwt;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class CommentController {

    private final CommentService commentService;
    private final JwtProvider jwtProvider;
    @ApiOperation(value = "리뷰에 댓글 등록", notes = "성공 시 true, 실패 시 false")
    @PostMapping("/feed/travel-review/{review_id}/comment")
    public ResponseEntity<?> commentSave(HttpServletRequest request, @PathVariable("review_id") Long review_id, CommentSaveRequestDto dto) throws Exception {
        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION).split(" ")[0];
        request.setAttribute("id", jwtProvider.getIdFromAccessToken(accessToken));
        String id = (String) request.getAttribute("id");
//        return new ResponseEntity<>(CommonResponse.getSuccessResponse(commentService.commentSave(id, review_id, dto)), HttpStatus.OK);
        try {
            commentService.commentSave(id, review_id, dto);
            return new ResponseEntity<>(true, HttpStatus.valueOf(201));
        } catch (Exception e) {
            //e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.valueOf(500));
        }
    }


    @ApiOperation(value = "리뷰의 댓글 조회", notes = "성공 시 리뷰, 실패 시 null")
    @GetMapping("/feed/travel-review/{review_id}/comment")
    public ResponseEntity<?> getCommentByReview(HttpServletRequest request, @PathVariable("review_id") Long review_id){
        try {
            List<CommentResDto> comments = commentService.findAllByReview(review_id);
            return ResponseEntity.ok(comments);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

//    @PutMapping("/{comment_id}")
//    public ResponseEntity<?> commentUpdate(@RequestParam("id") String id, @PathVariable("comment_id") Long comment_id, CommentUpdateRequestDto dto) {
////        return new ResponseEntity<>(CommonResponse.getSuccessResponse(commentService.commentUpdate(id, comment_id, dto)), HttpStatus.OK);
//        try {
//            commentService.commentUpdate(id, comment_id, dto);
//            return new ResponseEntity<>(true, HttpStatus.valueOf(201));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(false, HttpStatus.valueOf(500));
//        }
//    }
//
    @ApiOperation(value = "리뷰에 댓글 삭제", notes = "성공 시 true, 실패 시 false")
    @DeleteMapping("/feed/travel-review/comment/{comment_id}")
    public ResponseEntity<?> commentDelete(HttpServletRequest request, @PathVariable("comment_id") Long comment_id) throws Exception {
        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION).split(" ")[0];
        request.setAttribute("id", jwtProvider.getIdFromAccessToken(accessToken));
        String id = (String) request.getAttribute("id");
        try {
            commentService.commentDelete(id, comment_id);
            return new ResponseEntity<>(true, HttpStatus.valueOf(200));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.valueOf(400));
        }
    }



}
