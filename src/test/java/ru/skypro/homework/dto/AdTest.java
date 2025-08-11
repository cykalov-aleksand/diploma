package ru.skypro.homework.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdTest {

    @Test
    public void testConstructorAndGetters() {
        Ad ad = new Ad(1, "http://example.com/image.jpg", 100, "Test Title", 500);
        assertEquals(1, ad.getAuthor());
        assertEquals("http://example.com/image.jpg", ad.getImage());
        assertEquals(100, ad.getPk());
        assertEquals("Test Title", ad.getTitle());
        assertEquals(500, ad.getPrice());
    }

    @Test
    public void testSetters() {
        Ad ad = new Ad();
        ad.setAuthor(2);
        ad.setImage("http://example.com/newimage.jpg");
        ad.setPk(200);
        ad.setTitle("New Title");
        ad.setPrice(1000);
        assertEquals(2, ad.getAuthor());
        assertEquals("http://example.com/newimage.jpg", ad.getImage());
        assertEquals(200, ad.getPk());
        assertEquals("New Title", ad.getTitle());
        assertEquals(1000, ad.getPrice());
    }

    @Test
    public void testEquals() {
        Ad ad1 = new Ad(1, "http://example.com/image.jpg", 100, "Test Title", 500);
        Ad ad2 = new Ad(1, "http://example.com/image.jpg", 100, "Test Title", 500);
        Ad ad3 = new Ad(2, "http://example.com/image2.jpg", 101, "Another Title", 600);
        assertEquals(ad1, ad2);
        assertNotEquals(ad1, ad3);
    }

    @Test
    public void testHashCode() {
        Ad ad1 = new Ad(1, "http://example.com/image.jpg", 100, "Test Title", 500);
        Ad ad2 = new Ad(1, "http://example.com/image.jpg", 100, "Test Title", 500);
        assertEquals(ad1.hashCode(), ad2.hashCode());
    }

    @Test
    public void testToString() {
        Ad ad = new Ad(1, "http://example.com/image.jpg", 100, "Test Title", 500);
        String expected = "Ad(author=1, image=http://example.com/image.jpg, pk=100, title=Test Title, price=500)";
        assertEquals(expected, ad.toString());
    }
}
