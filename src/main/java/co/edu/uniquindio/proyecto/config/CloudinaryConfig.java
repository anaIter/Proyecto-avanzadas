package co.edu.uniquindio.proyecto.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dzb0jrdna");
        config.put("api_key", "118729328282733");
        config.put("api_secret", "vUizXXX88FIOiBMVBh5LopMAAXU");
        return new Cloudinary(config);
    }
}

