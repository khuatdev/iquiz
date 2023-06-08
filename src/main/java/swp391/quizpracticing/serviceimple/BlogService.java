/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swp391.quizpracticing.serviceimple;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import swp391.quizpracticing.dto.BlogDTO;
import swp391.quizpracticing.model.Blog;
import swp391.quizpracticing.model.Blogcategory;
import swp391.quizpracticing.repository.IBlogRepository;
import swp391.quizpracticing.service.IBlogService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mosena
 */
@Service
public class BlogService implements IBlogService {
    public static final int BLOGS_PER_PAGE = 4;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IBlogRepository iBlogRepository;
    private BlogDTO convertEntityToDTO(Blog entity){
        return modelMapper.map(entity,BlogDTO.class);
    }

    @Override
    public List<BlogDTO> getAllBlog() {
        List<BlogDTO> results = new ArrayList<>();
        List<Blog> blogs = iBlogRepository.findAll();
        for(Blog blog : blogs) {
            BlogDTO blogDTO = new BlogDTO();
            blogDTO.blogHomePage(blog);
            results.add(blogDTO);
        }
        return results;
    }
    @Override
    public List<BlogDTO> getFeaturedBlog(boolean isFeatured) {
        List<Blog> featuredBlog = iBlogRepository.findByFeaturing(isFeatured);
        List<BlogDTO> results = new ArrayList<>();
        for(Blog blog : featuredBlog) {
            BlogDTO blogDTO = new BlogDTO();
            blogDTO.blogHomePage(blog);
            results.add(blogDTO);
        }
        return results;
    }


    @Override
    public Page<Blog> getAllBlogsWithPagination(int pageNo) {
        Page<Blog> blogs = iBlogRepository.findAll(PageRequest.of(pageNo, BLOGS_PER_PAGE));
        return blogs;
    }

    @Override
    public List<BlogDTO> searchBlogByTitle(String searchTerm) {
        List<BlogDTO> results = new ArrayList<>();
        List<Blog> searchedBlog = iBlogRepository.findByTitleContainingIgnoreCase(searchTerm);
        for(Blog blog : searchedBlog) {
            BlogDTO blogDTO = new BlogDTO();
            blogDTO.blogHomePage(blog);
            results.add(blogDTO);
        }
        return results;
    }

    @Override
    public Blog getBlogById(Integer id) {
        return iBlogRepository.findById(id).get();

    }

    @Override
    public List<BlogDTO> getFeaturedLatestBlog(boolean isFeatured, int limit) {
        List<Blog> lastestBlog = iBlogRepository.findByFeaturingOrderByUpdatedDate(isFeatured, limit);
        List<BlogDTO> results = new ArrayList<>();
        for(Blog blog : lastestBlog) {
            BlogDTO blogDTO = new BlogDTO();
            blogDTO = convertEntityToDTO(blog);
            results.add(blogDTO);
        }
        return results;

    }

    @Override
    public List<BlogDTO> getLatestBlog(int limit) {
        List<Blog> lastestBlog = iBlogRepository.findByUpdatedDate(limit);
        List<BlogDTO> results = new ArrayList<>();
        for(Blog blog : lastestBlog) {
            BlogDTO blogDTO = new BlogDTO();
            blogDTO = convertEntityToDTO(blog);
            results.add(blogDTO);
        }
        return results;
    }

    @Override
    public List<BlogDTO> getFilteredBlog(List<Blogcategory> categories) {
        return null;
    }

}
