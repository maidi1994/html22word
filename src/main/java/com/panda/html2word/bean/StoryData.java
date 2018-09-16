package com.panda.html2word.bean;

import com.deepoove.poi.config.Name;
import com.deepoove.poi.data.DocxRenderData;
import lombok.Data;

@Data
public class StoryData {

    @Name("story_name")
    private String storyName;
    @Name("story_author")
    private String storyAuthor;
    private DocxRenderData segment;
    @Name("story_source")
    private String storySource;
    private String summary;
    private DocxRenderData html;


}
