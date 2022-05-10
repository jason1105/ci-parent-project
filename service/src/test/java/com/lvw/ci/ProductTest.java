package com.lvw.ci;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.Test;

public class ProductTest {
    @Test
    public void testGetPrice() {
        String[] productsId = { "551431304902473",
                "1039072467568652",
                "2167473150",
                "1019631501140809",
                "766493494056140",
                "766407366214860",
                "1200876654517001",
                "2165645281",
                "1762062099442756",
                "1996806911189573",
                "2032896066153367",
                "660263543169996",
                "721195481081737",
                "2167590277",
                "2164130188",
                "6919478321909275344",
                "2247942219594077",
                "1431612602065672",
                "2167473048",
                "864597824508484",
                "721177562994569",
                "2202473688359047",
                "498148463703625",
                "1293568349853271",
                "2166070181",
                "2167372413",
                "1960511568329801",
                "2167199903",
                "2166106344",
                "6919208615580905819",
                "2163449266",
                "2165647704",
                "6918864641533451291",
                "865028244116695",
                "1949221757770444",
                "2206873881934341",
                "2165645275",
                "2107074725714953",
                "908048407529239",
                "2166106348",
                "1996812800025157",
                "1772344913315784",
                "1707126449392066",
                "2168343534",
                "1720087817841028",
                "803766254076364",
                "2043147778947657",
                "2168092186",
                "1528679131623752",
                "625478079215433" };
        List<Product> products = Arrays.stream(productsId).map((id) -> {
            System.out.println("Add product:" + id);
            return new Product(id);
        }).collect(Collectors.toList());

        products.forEach((product) -> {
            try {
                System.out.println(product.id() + "'s price: " + product.price());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        System.out.println("Done!!");
    }
}
