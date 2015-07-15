/* Isaac Wismer
 *  Jun 15, 2015
 */
package nutrientcalculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Database {

    //main method for bug testing
    public static void main(String args[]) {
        Reader r = new Reader("data//FOOD_NM.txt");
        int length = r.getLength();
        for (int i = 0; i < length; i++) {
            Object databaseLine[] = r.getNextLine();
            System.out.println(databaseLine[0].toString() + ":" + databaseLine[1].toString());
        }
    }

    static DecimalFormat oneDecimal = new DecimalFormat("#,##0.0");

    public static ArrayList<Ingredient> search(String keyword) {
        double temp;
        //create new arraylist for the matched
        ArrayList<Ingredient> match = new ArrayList<>(0);
        int counter = 0, index;
        //create new arraylist for the search queryies
        ArrayList<String> query = new ArrayList<>(0);
        //separate the search string into queryies
        while (keyword.contains(",")) {
            index = keyword.indexOf(",");
            query.add(keyword.substring(0, index));
            keyword = keyword.substring(index + 1);
            if (keyword.substring(0, 1).equals(" ")) {
                keyword = keyword.substring(1);
            }
        }
        //add the remaining part of the string to the queries
        query.add(keyword);
        //read from the nutrients to check if they match the queries
        try {
            //read from the file
            Reader r = new Reader("data//FOOD_NM.txt");
            int length = r.getLength() - 1;
            //read each line and check if it matched the search terms
            for (int i = 0; i < length; i++) {
                Object databaseLine[] = r.getNextLine();
                boolean matches = true;
                //check if the food item matches the query
                for (int j = 0; j < query.size(); j++) {
                    if (!(databaseLine[1]).toString().contains(query.get(j))) {
                        matches = false;
                    }
                }
                //if it does add it to the list of matches
                if (matches) {
                    match.add(new Ingredient(Integer.parseInt(databaseLine[0].toString()), databaseLine[1].toString()));
                }
            }
        } catch (NullPointerException ex) {
            System.out.println("Error: " + ex.toString());
        }
        return match;
    }//End searchByName()

    /* This method saves the recipe to a file
     * @param The file path
     */
    public static void save(File file) throws FileNotFoundException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        PrintWriter p = new PrintWriter(file + ".txt");
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("recipe");
            doc.appendChild(rootElement);

            // title element
            Element title = doc.createElement("title");
            title.appendChild(doc.createTextNode(GUI.recipe.getTitle()));
            p.println(GUI.recipe.getTitle());
            rootElement.appendChild(title);
            // title element
            Element instructions = doc.createElement("instructions");
            instructions.appendChild(doc.createTextNode(GUI.recipe.getInstructions()));
            p.println(GUI.recipe.getInstructions());
            rootElement.appendChild(instructions);

            for (int i = 0; i < GUI.recipe.getIngredients().size(); i++) {
                Element ingredients = doc.createElement("ingredients");
                rootElement.appendChild(ingredients);
                Ingredient ing = GUI.recipe.getSingleIngredientIndex(i);

                Element id = doc.createElement("ID");
                id.appendChild(doc.createTextNode(ing.getID() + ""));
                ingredients.appendChild(id);
                p.println(ing.getID());

                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(ing.getName()));
                ingredients.appendChild(name);
                p.println(ing.getName());

                Element formattedName = doc.createElement("formattedName");
                formattedName.appendChild(doc.createTextNode(ing.getFormattedName()));
                ingredients.appendChild(formattedName);
                p.println(ing.getFormattedName());

                Element unitName = doc.createElement("unitName");
                unitName.appendChild(doc.createTextNode(ing.getUnit()));
                ingredients.appendChild(unitName);
                p.println(ing.getUnit());

                Element unitNum = doc.createElement("unitNum");
                unitNum.appendChild(doc.createTextNode(ing.getUnitNum() + ""));
                ingredients.appendChild(unitNum);
                p.println(ing.getUnitNum());

                Element fractionName = doc.createElement("fractionName");
                fractionName.appendChild(doc.createTextNode(ing.getFractionName()));
                ingredients.appendChild(fractionName);
                p.println(ing.getFractionName());

                Element fractionNum = doc.createElement("fractionNum");
                fractionNum.appendChild(doc.createTextNode(ing.getFractionNum() + ""));
                ingredients.appendChild(fractionNum);
                p.println(ing.getFractionNum());

                Element quantity = doc.createElement("quantity");
                quantity.appendChild(doc.createTextNode(ing.getQuantity() + ""));
                ingredients.appendChild(quantity);
                p.println(ing.getQuantity());

                for (int j = 0; j < ing.getMeasures().size(); j++) {
                    Element measures = doc.createElement("measures");
                    ingredients.appendChild(measures);
                    Measures m = ing.getSingleMeasureIndex(j);

                    Element measureID = doc.createElement("id");
                    measureID.appendChild(doc.createTextNode(m.getID() + ""));
                    measures.appendChild(measureID);
                    p.println(m.getID());

                    Element conversion = doc.createElement("conversion");
                    conversion.appendChild(doc.createTextNode(m.getConversion() + ""));
                    measures.appendChild(conversion);
                    p.println(m.getConversion());

                    Element measureName = doc.createElement("measureName");
                    measureName.appendChild(doc.createTextNode(m.getName()));
                    measures.appendChild(measureName);
                    p.println(m.getName());
                }
            }
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result;
            if (!file.toString().substring(file.toString().length() - 4, file.toString().length()).equals(".xml")) {
                result = new StreamResult(file + ".xml");
            } else {
                result = new StreamResult(file);
            }
            transformer.transform(source, result);
            System.out.println("File saved!");
            p.close();
        } catch (ParserConfigurationException | TransformerException ex) {
            System.out.println("XML Error: " + ex.toString());
        }
    }//End save()

    /* This method opens a saved recipe and re-assings the variable with the new data
     * @param The file path
     */
    public static void open(File file) {
        System.out.println(file);
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            //title
            NodeList nList = doc.getElementsByTagName("title");
            Node nNode = nList.item(0);
            Element eElement = (Element) nNode;
            GUI.recipe.setTitle(eElement.getTextContent());
            System.out.println(GUI.recipe.getTitle());
            //instructions
            nList = doc.getElementsByTagName("instructions");
            nNode = nList.item(0);
            eElement = (Element) nNode;
            GUI.recipe.setInstructions(eElement.getTextContent());
            System.out.println(GUI.recipe.getInstructions());
            //ingredients
            nList = doc.getElementsByTagName("ingredients");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    eElement = (Element) nNode;
                    Ingredient i = new Ingredient();
                    i.setID(Integer.parseInt(eElement.getElementsByTagName("ID").item(0).getTextContent()));
                    i.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                    i.setFormattedName(eElement.getElementsByTagName("formattedName").item(0).getTextContent());
                    i.setUnit(eElement.getElementsByTagName("unitName").item(0).getTextContent());
                    i.setUnitNum(Integer.parseInt(eElement.getElementsByTagName("unitNum").item(0).getTextContent()));
                    i.setFractionName(eElement.getElementsByTagName("fractionName").item(0).getTextContent());
                    i.setFractionNum(Integer.parseInt(eElement.getElementsByTagName("fractionNum").item(0).getTextContent()));
                    i.setQuantity(Integer.parseInt(eElement.getElementsByTagName("quantity").item(0).getTextContent()));

                    //measures
                    NodeList measureList = eElement.getElementsByTagName("measures");
                    for (int j = 0; j < measureList.getLength(); j++) {
                        Node mNode = measureList.item(j);
                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element mElement = (Element) nNode;
                            Measures m = new Measures();
                            m.setID(Integer.parseInt(mElement.getElementsByTagName("id").item(j).getTextContent()));
                            m.setConversion(Double.parseDouble(mElement.getElementsByTagName("conversion").item(j).getTextContent()));
                            m.setName(mElement.getElementsByTagName("measureName").item(j).getTextContent());
                            i.addMeasureFull(m);
                        }
                    }
                    GUI.recipe.addIngredient(i);
                }
            }
            for (int i = 0; i < GUI.recipe.getIngredients().size(); i++) {
                GUI.recipe.addListItem(GUI.recipe.getSingleIngredientIndex(i).getFormattedName());
            }
            GUI.listRecipe.setModel(GUI.recipe.getList());

        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            System.out.println("File Read Error: " + e.toString());
        }
    }//end open()

    /* This method creates the output that will be displayed for the user
     * @param the array with nutrient information, the name of the food, the index of the convertion rate and whether or not it is a custom portion
     * @return a string with the formatted output
     */
    public static String createOutput(Recipe recipe, Double[] recipeNutrients, boolean label) {
        String output;
        //make the title
        output = "Nutrition Facts for " + recipe.getTitle() + "\r\n\r\n";
        output += "Ingredients:\r\n";
        //print out the ingredients
        for (int i = 0; i < recipe.getList().getSize(); i++) {
            output += recipe.getList().getElementAt(i) + "\r\n";
        }
        output += "\r\n\r\n";
        //print out the instructions
        if (!recipe.getInstructions().equals("")) {
            output += "Directions\n";
            output += recipe.getInstructions();
            output += "\r\n\r\n";
        }
        if (!label) {//print out the info if its not a label
            Reader r = new Reader("data//NT_NM.txt");
            int length = r.getLength();
            for (int i = 0; i < length; i++) {
                Object aobj[] = r.getNextLine();
                int id = Integer.parseInt(aobj[0].toString());
                //makes sure there is data for the nutrient
                if (recipeNutrients[id] != null) {
                    //makes sure the value is not 0
                    if (!oneDecimal.format((recipeNutrients[id])).equals("0.0")) {
                        //add the nutrient name
                        output += aobj[2].toString();
                        //add the periods to line up the output
                        int dots = (80 - aobj[2].toString().length());
                        for (int j = 0; j < dots; j++) {
                            output += ".";
                        }
                        //add the nutrient information
                        output += oneDecimal.format((recipeNutrients[id])) + aobj[1] + "\r\n";
                    }
                }
            }
        } else {//if it is a label
            //make the percent decimal format
            DecimalFormat onePer = new DecimalFormat("#,##0%");
            //begin printing out the label
            output += "Makes " + recipe.getServings() + ". The label is made for that serving size.\r\n";
            output += "=========================================\r\n";
            output += "|Nutrition Facts\t\t\t|\r\n";
            output += "|Per " + recipe.getServingEng() + "/par " + recipe.getServingFre();
            double servingsD = recipe.getServings();
            //the if staments checks the size of the number to determine the 
            //number of tabs that are necissary to line up the label
            if (recipe.getServingEng().length() + recipe.getServingFre().length() < 5) {
                output += "\t\t\t\t";
            } else if (recipe.getServingEng().length() + recipe.getServingFre().length() >= 30) {
            } else if (recipe.getServingEng().length() + recipe.getServingFre().length() >= 22) {
                output += "\t";
            } else if (recipe.getServingEng().length() + recipe.getServingFre().length() >= 14) {
                output += "\t\t";
            } else {
                output += "\t\t\t";
            }
            output += "|\r\n";
            output += "|=======================================|\r\n";
            output += "|Amount\t\t\t% Daily Value\t|\r\n";
            output += "|Teneur\t\t % valeur quotodoenne\t|\r\n";
            output += "|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\r\n";
            output += "|Calories/Calories " + (int) (recipeNutrients[208] / servingsD) + "\t\t";
            if ((recipeNutrients[208] / servingsD) < 10000) {
                output += "\t";
            }
            output += "|\r\n";
            output += "|---------------------------------------|\r\n";
            output += "|Fat/Lipides " + (int) (recipeNutrients[204] / servingsD) + "g\t\t";
            if ((int) (recipeNutrients[204] / servingsD) <= 9) {
                output += "\t";
            }
            output += onePer.format((recipeNutrients[204] / servingsD) / 60.0) + "\t|\r\n";
            output += "|---------------------------------------|\r\n";
            output += "|\tSaturated/Satures " + (int) (recipeNutrients[606] / servingsD) + "g\t" + onePer.format((recipeNutrients[606] / servingsD) / 20.0) + "\t|\r\n";
            output += "|\t+ Trans/trans " + (int) (recipeNutrients[605] / servingsD) + "g\t\t|\r\n";
            output += "|---------------------------------------|\r\n";
            output += "|Cholesterol/Cholesterol " + (int) (recipeNutrients[601] / servingsD) + "mg\t";
            if (recipeNutrients[601] / servingsD < 1000) {
                output += "\t";
            }
            output += "|\r\n";
            output += "|---------------------------------------|\r\n";
            output += "|Sodium/Sodium " + (int) (recipeNutrients[307] / servingsD) + "mg\t\t" + onePer.format((recipeNutrients[307] / servingsD) / 2400.0) + "\t|\r\n";
            output += "|---------------------------------------|\r\n";
            output += "|Carbohydrate/Glucides " + (int) (recipeNutrients[205] / servingsD) + "g\t" + onePer.format((recipeNutrients[205] / servingsD) / 300.0) + "\t|\r\n";
            output += "|---------------------------------------|\r\n";
            output += "|\tFibre/Fibres " + (int) (recipeNutrients[291] / servingsD) + "g\t";
            if ((recipeNutrients[291] / servingsD) < 10) {
                output += "\t";
            }
            output += onePer.format((recipeNutrients[291] / servingsD) / 25.0) + "\t|\r\n";
            output += "|\t--------------------------------|\r\n";
            output += "|\tSugars/Sucres " + (int) (recipeNutrients[269] / servingsD) + "g\t\t|\r\n";
            output += "|---------------------------------------|\r\n";
            output += "|Protein/Proteines " + (int) (recipeNutrients[203] / servingsD) + "g\t\t";
            if (recipeNutrients[203] / servingsD < 1000) {
                output += "\t";
            }
            output += "|\r\n";
            output += "|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|\r\n";
            output += "|Vitamin A/Vitamine A\t\t**%\t|\r\n";
            output += "|---------------------------------------|\r\n";
            output += "|Vitamin C/Vitamine C\t\t" + onePer.format((recipeNutrients[401] / servingsD) / 60.0) + "\t|\r\n";
            output += "|---------------------------------------|\r\n";
            output += "|Calcium/Calcium\t\t" + onePer.format((recipeNutrients[301] / servingsD) / 1100.0) + "\t|\r\n";
            output += "|---------------------------------------|\r\n";
            output += "|Iron/fer\t\t\t" + onePer.format((recipeNutrients[303] / servingsD) / 14.0) + "\t|\r\n";
            output += "=========================================\r\n";
        }
        return output;
    }//End createOutput()

    public static boolean checkMeasuresML(int ID) {
        //get conversion rates
        getConv(ID);
        //check the conversions for mL measurements
        for (int i = 0; i < GUI.recipe.getSingleIngredientID(ID).getMeasures().size(); i++) {
            int measureID = GUI.recipe.getSingleIngredientID(ID).getSingleMeasureIndex(i).getID();
            if (measureID > 340 && measureID < 380) {
                return true;
            } else if (measureID > 384 && measureID < 394) {
                return true;
            } else if (measureID > 412 && measureID < 427) {
                return true;
            } else if (measureID == 439 || measureID == 388
                    || measureID == 389 || measureID == 923
                    || measureID == 932 || measureID == 638
                    || measureID == 640 || measureID == 641
                    || measureID == 430 || measureID == 939
                    || measureID == 943 || measureID == 429
                    || measureID == 428 || measureID == 383) {
                return true;
            } else if (measureID >= 385 && measureID <= 387) {
                return true;
            }
        }
        return false;
    }//End checkMeasurementML()

    public static void getConv(int ID) {
        int temp;
        int counter;
        System.out.println("Getting conversion rates for food ID = " + ID);
        Reader r = new Reader("data//CONV_FAC.txt");
        int length = r.getLength() - 1;
        for (int i = 0; i < length; i++) {
            Object aobj[] = r.getNextLine();
            temp = Integer.parseInt(aobj[0].toString());
            //get the measure conversion factor and measure ID
            if (ID == temp && Integer.parseInt(aobj[1].toString()) != 1572) {
                GUI.recipe.getSingleIngredientID(ID).addMeasure(Integer.parseInt(aobj[1].toString()), Double.parseDouble(aobj[2].toString()));
            }

        }
        counter = 0;
        r = new Reader("data//MEASURE.txt");
        length = r.getLength() - 1;
        for (int i = 0; i < length; i++) {
            Object aobj[] = r.getNextLine();
            if (GUI.recipe.getSingleIngredientID(ID).getMeasures().size() == counter) {
                break;
            }
            //get the measure name
            //temp = Double.parseDouble(conversionRates.get(counter)[0].toString());
            int measureID = GUI.recipe.getSingleIngredientID(ID).getSingleMeasureIndex(counter).getID();
            temp = Integer.parseInt(aobj[0].toString());
            if (measureID == temp && measureID != 1572) {
                // conversionRates.get(counter)[2] = aobj[1];//Add measure Name
                GUI.recipe.getSingleIngredientID(ID).getSingleMeasureID(temp).setName(aobj[1].toString());
                counter++;
                //dont allow no measure specified
            } else if (measureID == 1572) {
                //conversionRates.remove(counter);
                GUI.recipe.getSingleIngredientID(ID).removeSingleMeasure(counter);
            }
        }
    }//end getConv

    public static DefaultComboBoxModel measures(int ID) {
        //get the conversion rates for the ingredient
        //getConv(ID);
        //create the combobox model
        DefaultComboBoxModel measures = new DefaultComboBoxModel();
        //add the names of the potions to the cmb model
        for (int i = 0; i < GUI.recipe.getSingleIngredientID(ID).getMeasures().size(); i++) {
            measures.addElement(GUI.recipe.getSingleIngredientID(ID).getSingleMeasureIndex(i).getName());
        }
        return measures;
    }//End convRate()

    /* This method gets the information for each ingredient needed to create the label/output
     * @param the array of the ingredients, whether or not it's a label, the number of servings and the english and french serving names
     * @return a string with the output
     */
    public static String recipe(Recipe recipe, boolean label) {
        Double[] recipeNutrients = new Double[870], indiviual;
        double nutrientConv = 1.0, portionSize = 100.0;
        //make the recipe nutrients all set to 0
        for (int i = 0; i < 870; i++) {
            recipeNutrients[i] = 0.0;
        }
        for (int i = 0; i < recipe.size(); i++) {
            //get the nutrient data for the food
            indiviual = getNutrientData(recipe.getSingleIngredientIndex(i).getID());
            System.out.println("Ingredient: " + recipe.getSingleIngredientIndex(i).getName());
            System.out.println("Measurement: " + recipe.getSingleIngredientIndex(i).getFractionName());
            //convert the nutrients to be for the specified quantity
            if (recipe.getSingleIngredientIndex(i).getUnit().equals("g")) {
                //grams just have to be divided by 100 to get the conversion rate
                nutrientConv = ((double) recipe.getSingleIngredientIndex(i).getQuantity()) / 100.0;
            } else if (recipe.getSingleIngredientIndex(i).getUnit().equals("Metric Cooking Measures")) {
                //get the correct portion size (measurement)
                int fractionNum = recipe.getSingleIngredientIndex(i).getFractionNum();
                if (fractionNum == 0) {
                    portionSize = 5.0 / 4.0;
                } else if (fractionNum == 1) {
                    portionSize = 5.0 / 2.0;
                } else if (fractionNum == 2) {
                    portionSize = 5.0;
                } else if (fractionNum == 3) {
                    portionSize = 15.0;
                } else if (fractionNum == 4) {
                    portionSize = 250.0 / 4.0;
                } else if (fractionNum == 5) {
                    portionSize = 250.0 / 3.0;
                } else if (fractionNum == 6) {
                    portionSize = 250.0 / 2.0;
                } else {
                    portionSize = 250.0;
                }
                //multiply the measure by the specified quantity
                portionSize *= (double) recipe.getSingleIngredientIndex(i).getQuantity();
                System.out.println("Portion Size = " + portionSize + "mL");
            } else if (recipe.getSingleIngredientIndex(i).getUnit().equals("Other")) {
                //look for the matching portion size
                for (int j = 0; j < recipe.getSingleIngredientIndex(i).getMeasures().size(); j++) {
                    if (recipe.getSingleIngredientIndex(i).getFractionName().equals(recipe.getSingleIngredientIndex(i).getSingleMeasureIndex(j).getName())) {
                        nutrientConv = recipe.getSingleIngredientIndex(i).getSingleMeasureIndex(j).getConversion() * recipe.getSingleIngredientIndex(i).getQuantity();
                        j = recipe.getSingleIngredientIndex(i).getMeasures().size() + 1;
                    }
                }
            }
            if (recipe.getSingleIngredientIndex(i).getUnit().equals("Metric Cooking Measures") || recipe.getSingleIngredientIndex(i).getUnit().equals("mL")) {
                for (int j = 0; j < recipe.getSingleIngredientIndex(i).getMeasures().size(); j++) {
                    int ID = recipe.getSingleIngredientIndex(i).getSingleMeasureIndex(j).getID();
                    double conversion = recipe.getSingleIngredientIndex(i).getSingleMeasureIndex(j).getConversion();
                    if (ID > 340 && ID < 380) {
                        nutrientConv = portionSize * (conversion / 100.0);
                        break;
                    } else if (ID == 383) {
                        nutrientConv = portionSize * (conversion / 125.0);
                        break;
                    } else if (ID == 415 || ID == 418 || ID == 939 || ID == 943) {
                        nutrientConv = portionSize * (conversion / 250.0);
                        break;
                    } else if (ID == 439) {
                        nutrientConv = portionSize * (conversion / 5.0);
                        break;
                    } else if (ID == 413) {
                        nutrientConv = portionSize * (conversion / 200.0);
                        break;
                    } else if (ID == 414) {
                        nutrientConv = portionSize * (conversion / 225.0);
                        break;
                    } else if (ID == 419) {
                        nutrientConv = portionSize * (conversion / 275.0);
                        break;
                    } else if (ID == 428) {
                        nutrientConv = portionSize * (conversion / 30.0);
                        break;
                    } else if (ID == 429) {
                        nutrientConv = portionSize * (conversion / 300.0);
                        break;
                    } else if (ID == 430) {
                        nutrientConv = portionSize * (conversion / 375.0);
                        break;
                    } else if (ID >= 385 && ID <= 387) {
                        nutrientConv = portionSize * (conversion / 15.0);
                        break;
                    } else if (ID == 388) {
                        nutrientConv = portionSize * (conversion / 150.0);
                        break;
                    } else if (ID == 389) {
                        nutrientConv = portionSize * (conversion / 175.0);
                        break;
                    } else if (ID == 638) {
                        nutrientConv = portionSize * (conversion / 50.0);
                        break;
                    } else if (ID == 640) {
                        nutrientConv = portionSize * (conversion / 60.0);
                        break;
                    } else if (ID == 641) {
                        nutrientConv = portionSize * (conversion / 75.0);
                        break;
                    } else if (ID == 923) {
                        nutrientConv = portionSize * (conversion / 45.0);
                        break;
                    } else if (ID == 932) {
                        nutrientConv = portionSize * (conversion / 80.0);
                        break;
                    }
                }
            }
            System.out.println("Conversion Rate = " + nutrientConv + "\n End of Ingredient\n");
            //add the individual nutrients to the recipe ingredients
            for (int j = 0; j < 870; j++) {
                if (indiviual[j] != null) {
                    recipeNutrients[j] += (indiviual[j] * nutrientConv);
                }
            }
        }
        //ask for the name of the recipe
        if (recipe.getTitle().equals("")) {
            recipe.setTitle(JOptionPane.showInputDialog("Please enter the name of the recipe"));
        }
        return createOutput(recipe, recipeNutrients, label);
    }//End recipe()

    /* This method collects all the nutrient amounts
     * @param the food ID of the food item
     * @return an array with the nutrient ID and the amount of that nutrient
     */
    public static Double[] getNutrientData(int ID) {
        int temp,
                counter = 0;
        double tempd;
        Double[] nutrients = new Double[870];
        Reader r = new Reader("data//NT_AMT.txt");
        int length = r.getLength();
        for (int i = 0; i < length; i++) {
            Object aobj[] = r.getNextLine();
            temp = Integer.parseInt(aobj[0].toString());
            //check if the food on the line matches the ID of the target
            if (ID == temp) {
                //if it does get the nutrent amount and add it to the array
                nutrients[Integer.parseInt(aobj[1].toString())] = Double.parseDouble(aobj[2].toString());
            }
        }
        return nutrients;
    }//End getData()

}
