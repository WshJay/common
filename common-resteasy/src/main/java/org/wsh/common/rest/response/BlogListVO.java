package org.wsh.common.rest.response;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.enums.blog.BlogStatus;
import org.wsh.common.enums.blog.Privacy;
import org.wsh.common.model.blog.BlogCounterDO;
import org.wsh.common.model.blog.BlogDO;
import org.wsh.common.util.date.DateUtil;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-02-10 14:31
 */
public class BlogListVO {

    /**
     * ID
     */
    @Setter
    @Getter
    private Long id;

    /**
     * 标题
     */
    @Setter
    @Getter
    private String title;

    /**
     * 封面地址
     */
    @Setter
    @Getter
    private String coverUrl;

    /**
     * 内容ID
     */
    @Setter
    @Getter
    private Long contentId;

    /**
     * 内容
     */
    @Setter
    @Getter
    private String content;

    /**
     * 公开程度[private:私有,public:公开]
     */
    @Setter
    @Getter
    private Privacy privacy;

    /**
     * 状态['SCREEN':屏蔽,'MAL':正常]
     */
    @Setter
    @Getter
    private BlogStatus status;

    /**
     * 作者ID
     */
    @Setter
    @Getter
    private Long authorId;

    /**
     * 作者名称
     */
    @Setter
    @Getter
    private String authorName;

    /**
     * 浏览量
     */
    @Setter
    @Getter
    private int viewNum;

    /**
     * 评论数量
     */
    @Setter
    @Getter
    private int commentNum;

    /**
     * 赞数量
     */
    @Setter
    @Getter
    private int praiseNum;

    /**
     * 月
     */
    @Setter
    @Getter
    private String month;

    /**
     * 日
     */
    @Setter
    @Getter
    private String day;

    public BlogListVO(BlogDO blogDO) {
        this.id = blogDO.getId();
        this.title = blogDO.getTitle();
        this.coverUrl = blogDO.getCoverUrl();
        this.contentId = blogDO.getContentId();
        this.privacy = blogDO.getPrivacy();
        this.status = blogDO.getStatus();
        this.authorId = blogDO.getAuthorId();
        this.month = DateUtil.parseDate(blogDO.getGmtModified(),DateUtil.MM);
        this.day = DateUtil.parseDate(blogDO.getGmtModified(),DateUtil.DD);
    }

    public BlogListVO counterToBlogVO(BlogListVO blogListVO, BlogCounterDO blogCounterDO) {
        blogListVO.setViewNum(blogCounterDO.getViewNum());
        blogListVO.setCommentNum(blogCounterDO.getCommentNum());
        blogListVO.setPraiseNum(blogCounterDO.getPraiseNum());
        return blogListVO;
    }

    public void counterToBlogVO(BlogCounterDO blogCounterDO) {
        this.viewNum = blogCounterDO.getViewNum();
        this.commentNum = blogCounterDO.getCommentNum();
        this.praiseNum = blogCounterDO.getPraiseNum();
    }
}
