package com.eatThat.application.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eatThat.application.services.DietPlanService;


@Controller
public class UploadController {

    private final String UPLOAD_DIR = "./uploads/";
    private List<String> headers = new ArrayList<String>();
    private Map<String, Integer> headerMap = new HashMap<String, Integer>();
    private Map<String, Integer> categoryMap = new HashMap<String, Integer>();
    private Map<String, String> nutritionMap = new HashMap<String, String>();
    private int foodItemId = 0;
    private String foodItemName;



    private List<String> categories = new ArrayList<String>();

    @Autowired
	DietPlanService dietPlanService;

    @GetMapping("/")
    public String homepage() {
        return "index";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {

        // check if file is empty
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // save the file on the local file system
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            
            readExcel(Paths.get(UPLOAD_DIR).toString(),fileName,"All Foods");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        

        // return success response
        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');

        return "redirect:/web/home";
    }
    
    
    
    @SuppressWarnings("unlikely-arg-type")
	public void readExcel(String filePath,String fileName,String sheetName) throws IOException{
    	
    	
	    File file =    new File(filePath+"\\"+fileName);
	    //Create an object of FileInputStream class to read excel file
	    FileInputStream inputStream = new FileInputStream(file);
	    Workbook eatThat = null;
	    //Find the file extension by splitting file name in substring  and getting only extension name
	    String fileExtensionName = fileName.substring(fileName.indexOf("."));
	    //Check condition if the file is xlsx file
	    if(fileExtensionName.equals(".xlsx")){
	    //If it is xlsx file then create object of XSSFWorkbook class
	    	eatThat = new XSSFWorkbook(inputStream);
	    }
	    //Check condition if the file is xls file
	    else if(fileExtensionName.equals(".xls")){
	        //If it is xls file then create object of HSSFWorkbook class
	    	eatThat = new HSSFWorkbook(inputStream);
	    }
	    Sheet foodItems = eatThat.getSheet(sheetName);
	    //Find number of rows in excel file
	    int rowCount = foodItems.getLastRowNum()-foodItems.getFirstRowNum();
	    //Create a loop over all the rows of excel file to read it

    	DataFormatter formatter = new DataFormatter();
    	headers.clear();
    	categories.clear();
	    for (int i = 0; i < rowCount+1; i++) {
	        Row row = foodItems.getRow(i);
	        //Create a loop to print cell values in a row
	        for (int j = 0; j < row.getLastCellNum(); j++) {
	            //Print Excel data in console
	        	if(i == 0 && j >= 7) {//Read headers & insert diet plans
	        		if(row.getCell(j) != null)
	        		headers.add(formatter.formatCellValue(row.getCell(j)));
	        	}
	        	
	        	if(i>1 && j == 6)
	        	{
		            System.out.println(formatter.formatCellValue(row.getCell(j))+ "and " + formatter.formatCellValue(row.getCell(j-1)));

	        		if(!categories.contains(formatter.formatCellValue(row.getCell(j))) && formatter.formatCellValue(row.getCell(j-1)) != null  && formatter.formatCellValue(row.getCell(j-1)) != "") {
	        			categories.add(formatter.formatCellValue(row.getCell(j)));
	        		}
	        	}
	        	
//	        	if(i > 0 )
//	            System.out.print(formatter.formatCellValue(row.getCell(j))+ "||");
	        }
	        System.out.println();   
	    }
	    dietPlanService.truncateTables();
	    headerMap =	dietPlanService.insertDietPlans(headers);
	    categoryMap =	dietPlanService.insertCategories(categories);
	    
	    for (int i = 0; i < rowCount+1; i++) {
	        Row row = foodItems.getRow(i);
	        //Create a loop to print cell values in a row
	        for (int j = 0; j < row.getLastCellNum(); j++) {
	        	
	        	if(i > 6 && formatter.formatCellValue(row.getCell(j)).trim().equals("Calories") || i == rowCount)
        		{
        			//insert nutritiion & clear map.
	        		dietPlanService.insertNutritionInfo(nutritionMap, foodItemId,foodItemName );
        			nutritionMap.clear();
        		}
	        	if (i > 1 && j == 2)
	        	{
        			nutritionMap.put(formatter.formatCellValue(row.getCell(j)), formatter.formatCellValue(row.getCell(j+1)));
	        	}
	        	
	        	System.out.println("Rows zero value is " + row.getCell(0));
	        	
	        	if(i > 1 && formatter.formatCellValue(row.getCell(0)) !=null && j >6 ) {
        			//insert food item & keep the food item value
		        	System.out.println("cell values " + row.getCell(j));

        			if(formatter.formatCellValue(row.getCell(j)).trim().equalsIgnoreCase("Yes")) {
        			Row	dietRow = foodItems.getRow(0);
            			foodItemId = dietPlanService.insertFoodItem(formatter.formatCellValue(row.getCell(1)) , formatter.formatCellValue(row.getCell(4)), formatter.formatCellValue(row.getCell(5)),categoryMap.get(formatter.formatCellValue(row.getCell(6))),headerMap.get(formatter.formatCellValue(dietRow.getCell(j))));
            			foodItemName = formatter.formatCellValue(row.getCell(1));
        			}
        		}
	        	
	        	if(i > 0 )
	            System.out.print(formatter.formatCellValue(row.getCell(j))+ "||");
	        }
	        System.out.println();   
	    }
	    
	    
	    }  

}