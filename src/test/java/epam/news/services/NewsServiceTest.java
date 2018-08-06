package epam.news.services;

import epam.news.dao.impl.NewsDAOImpl;
import epam.news.model.entity.Comment;
import epam.news.model.entity.News;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NewsServiceTest {

    private News news;

    @Mock
    private NewsDAOImpl mockedNewsDaoImpl;

    @Before
    public void setUp() {
        news = new News();
        news.setContent("testContent");
        news.setBrief("testBrief");
        news.setTitle("testTitle");
        news.setNewsId(10L);
    }

    @Test
    public void showAllNews() {
        List<News> newsList = new ArrayList<>();
        newsList.add(news);
        when(mockedNewsDaoImpl.read()).thenReturn(newsList);
        List<News> newsListResult = mockedNewsDaoImpl.read();
        verify(mockedNewsDaoImpl).read();
        assertNotNull(newsListResult);
    }

    @Test
    public void selectedNews() {
        when(mockedNewsDaoImpl.findById(10L)).thenReturn(news);
        News newsResult = mockedNewsDaoImpl.findById(10L);
        assertNotNull(newsResult);
        assertEquals(10L, newsResult.getNewsId().longValue());
    }


    @Test
    public void addNews() {
        doReturn(true).when(mockedNewsDaoImpl).create(news);
        News addedNews = news;
        mockedNewsDaoImpl.create(addedNews);
        verify(mockedNewsDaoImpl).create(addedNews);
        assertEquals(addedNews.getTitle(), "testTitle");
    }


    @Test
    public void deleteNews() {
        doReturn(news).when(mockedNewsDaoImpl).delete(news);

        mockedNewsDaoImpl.delete(news);
        verify(mockedNewsDaoImpl).delete(news);

        assertEquals(news.getTitle(), "testTitle");
    }

    @Test
    public void deleteComment() {
        Comment comment = new Comment();
        comment.setAuthor("UpdatedtestAuthor");
        comment.setDescription("UpdatedtestDescription");
        comment.setNewsId(10L);
        comment.setDateCreated(new Date());
        news.getCommentList().add(comment);
        doReturn(true).when(mockedNewsDaoImpl).update(news);
        mockedNewsDaoImpl.update(news);
        for (Comment updatedComment : news.getCommentList()) {
            assertEquals(updatedComment.getAuthor(), "UpdatedtestAuthor");
        }
        verify(mockedNewsDaoImpl).update(news);
    }
}