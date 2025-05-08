package com.example.demo.controller;

import com.example.demo.dto.HeroDTO;
import com.example.demo.entity.Hero;
import com.example.demo.mapper.HeroMapper;
import com.example.demo.repository.HeroRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final HeroRepository heroRepository;
    private final HeroMapper heroMapper;

    public HeroController(HeroRepository heroRepository, HeroMapper heroMapper) {
        this.heroRepository = heroRepository;
        this.heroMapper = heroMapper;
    }

    @GetMapping
    public List<HeroDTO> getAllHeroes() {
        return heroRepository.findAll().stream()
                .map(heroMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public Hero getHeroById(@PathVariable Long id) {
        return heroRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public HeroDTO updateHero(@PathVariable Long id, @RequestBody HeroDTO heroDTO) {
        Hero existingHero = heroRepository.findById(id).orElseThrow(() -> new RuntimeException("Hero not found"));
        existingHero.setName(heroDTO.getName());
        existingHero.setSuperpowers(heroDTO.getSuperpowers());
        String defaultImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRRGdLkYMo69JzWGPLPzvnBDwC_qG-M2xaLtw&s";
        String imageUrl = defaultImageUrl;
        try {
            String apiUrl = "https://gateway.marvel.com/v1/public/characters" +
                    "?apikey=ec27418913128e765db8d752465d3ab3" +
                    "&ts=1746533381" +
                    "&hash=a5fc45169923e15c608ae97435386e6c" +
                    "&name=" + java.net.URLEncoder.encode(heroDTO.getName(), java.nio.charset.StandardCharsets.UTF_8);

            java.net.URL url = new java.net.URL(apiUrl);
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                java.io.InputStream inputStream = connection.getInputStream();
                java.util.Scanner scanner = new java.util.Scanner(inputStream).useDelimiter("\\A");
                String response = scanner.hasNext() ? scanner.next() : "";
                scanner.close();
                org.json.JSONObject json = new org.json.JSONObject(response);
                org.json.JSONArray results = json.getJSONObject("data").getJSONArray("results");
                if (results.length() > 0) {
                    org.json.JSONObject heroJson = results.getJSONObject(0);
                    org.json.JSONObject thumbnail = heroJson.getJSONObject("thumbnail");
                    String path = thumbnail.getString("path");
                    String extension = thumbnail.getString("extension");
                    imageUrl = path + "." + extension;
                }
            }
        } catch (Exception e) {
            System.err.println("Error retrieving hero image: " + e.getMessage());
        }
        existingHero.setImage(imageUrl);
        return heroMapper.toDto(heroRepository.save(existingHero));
    }
}
