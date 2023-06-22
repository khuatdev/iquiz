/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package swp391.quizpracticing.service;

import org.springframework.data.domain.Page;
import swp391.quizpracticing.dto.SliderDTO;
import swp391.quizpracticing.model.Slider;

import java.util.List;
/**
 *
 * @author Mosena
 */
public interface ISliderService {
    List<SliderDTO> getAllSlider();

    List<SliderDTO> searchSliderByTitle(String searchTerm);

 //   List<SliderDTO> getFeaturedSlider(boolean isFeatured);

    Page<Slider> getAllSlidersWithPagination(int pageNo);

//    List<PostsDTO> getFilteredPosts(List<PostCategory> categories);
    Slider getSliderById(Integer id);

    List<SliderDTO> getAllSlidersForHomepage();
}
