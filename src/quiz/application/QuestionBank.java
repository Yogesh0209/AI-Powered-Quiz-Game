package quiz.application;

import java.util.*;

public class QuestionBank {

    // MCQ Questions: {Question, Option1, Option2, Option3, Option4, CorrectAnswer}
    private static String mcqQuestions[][] = {
        {"What is the capital of France?", "Paris", "Berlin", "Madrid", "Rome", "Paris"},
        {"Which planet is known as the Red Planet?", "Earth", "Mars", "Jupiter", "Venus", "Mars"},
        {"Who developed the theory of relativity?", "Isaac Newton", "Albert Einstein", "Galileo Galilei", "Nikola Tesla", "Albert Einstein"},
        {"What is the largest mammal?", "Elephant", "Blue Whale", "Giraffe", "Hippopotamus", "Blue Whale"},
        {"Which gas do plants absorb?", "Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen", "Carbon Dioxide"},
        {"Who painted the Mona Lisa?", "Pablo Picasso", "Leonardo da Vinci", "Vincent van Gogh", "Michelangelo", "Leonardo da Vinci"},
        {"What is the boiling point of water at sea level?", "90°C", "100°C", "120°C", "80°C", "100°C"},
        {"Which is the smallest prime number?", "1", "2", "3", "5", "2"},
        {"What is the currency of Japan?", "Yen", "Dollar", "Won", "Euro", "Yen"},
        {"Which continent is the Sahara Desert located on?", "Asia", "Africa", "Australia", "North America", "Africa"},
        {"Who is the author of 'Romeo and Juliet'?", "William Shakespeare", "Charles Dickens", "Jane Austen", "Mark Twain", "William Shakespeare"},
        {"Which is the fastest land animal?", "Cheetah", "Horse", "Leopard", "Tiger", "Cheetah"},
        {"What is the chemical symbol for gold?", "Go", "Au", "Ag", "Gd", "Au"},
        {"Which planet has the most moons?", "Earth", "Jupiter", "Mars", "Saturn", "Saturn"},
        {"What is the tallest mountain in the world?", "K2", "Kangchenjunga", "Everest", "Makalu", "Everest"},
        {"Who invented the telephone?", "Alexander Graham Bell", "Thomas Edison", "Guglielmo Marconi", "Nikola Tesla", "Alexander Graham Bell"},
        {"Which organ purifies blood in the human body?", "Lungs", "Liver", "Kidneys", "Heart", "Kidneys"},
        {"What is the hardest natural substance?", "Iron", "Diamond", "Gold", "Steel", "Diamond"},
        {"Which ocean is the largest?", "Atlantic Ocean", "Indian Ocean", "Pacific Ocean", "Arctic Ocean", "Pacific Ocean"},
        {"How many players are on a standard soccer team?", "9", "10", "11", "12", "11"},
        {"Which country is known as the Land of the Rising Sun?", "China", "Japan", "Thailand", "Vietnam", "Japan"},
        {"What is the square root of 144?", "10", "12", "14", "16", "12"},
        {"Which is the smallest planet in the solar system?", "Mercury", "Mars", "Venus", "Pluto", "Mercury"},
        {"Which blood type is known as the universal donor?", "A", "B", "O", "AB", "O"},
        {"Which metal is liquid at room temperature?", "Mercury", "Lead", "Aluminium", "Copper", "Mercury"},
        {"In which year did World War II end?", "1945", "1939", "1944", "1950", "1945"},
        {"Which is the longest river in the world?", "Amazon River", "Nile River", "Yangtze River", "Mississippi River", "Nile River"},
        {"Which planet is closest to the sun?", "Mercury", "Venus", "Earth", "Mars", "Mercury"},
        {"Which gas is most abundant in Earth's atmosphere?", "Oxygen", "Nitrogen", "Carbon Dioxide", "Hydrogen", "Nitrogen"},
        {"Who wrote 'Pride and Prejudice'?", "Jane Austen", "Emily Brontë", "Charlotte Brontë", "Louisa May Alcott", "Jane Austen"},
        {"Which instrument has keys, pedals, and strings?", "Piano", "Guitar", "Violin", "Flute", "Piano"},
        {"What is the largest organ in the human body?", "Brain", "Heart", "Skin", "Liver", "Skin"},
        {"Which country invented pizza?", "France", "Italy", "Greece", "Spain", "Italy"},
        {"What is the freezing point of water?", "0°C", "100°C", "−10°C", "10°C", "0°C"},
        {"Who discovered penicillin?", "Alexander Fleming", "Louis Pasteur", "Marie Curie", "Joseph Lister", "Alexander Fleming"},
        {"Which sport uses the term 'love'?", "Tennis", "Badminton", "Squash", "Cricket", "Tennis"},
        {"Which element has the chemical symbol 'O'?", "Oxygen", "Osmium", "Ozone", "Opium", "Oxygen"},
        {"Which is the largest internal organ in humans?", "Heart", "Liver", "Lungs", "Stomach", "Liver"},
        {"What is the capital city of Australia?", "Sydney", "Melbourne", "Canberra", "Canterbury", "Canberra"},
        {"Which scientist proposed the three laws of motion?", "Einstein", "Newton", "Kepler", "Galileo", "Newton"},
        {"Which ocean lies between Africa and Australia?", "Pacific", "Atlantic", "Indian", "Southern", "Indian"},
        {"Who painted 'The Starry Night'?", "Van Gogh", "Picasso", "Monet", "Da Vinci", "Van Gogh"},
        {"What is H2O commonly known as?", "Hydrogen Peroxide", "Water", "Salt", "Oxygen", "Water"},
        {"Which is the coldest place on Earth?", "Antarctica", "Siberia", "Alaska", "Greenland", "Antarctica"},
        {"Which gas do humans exhale?", "Oxygen", "Carbon Dioxide", "Nitrogen", "Helium", "Carbon Dioxide"},
        {"Which city is known as the Big Apple?", "Los Angeles", "Chicago", "New York City", "San Francisco", "New York City"},
        {"Which is the smallest bone in the human body?", "Stapes", "Femur", "Ulna", "Tibia", "Stapes"},
        {"What is the capital of Canada?", "Toronto", "Ottawa", "Vancouver", "Montreal", "Ottawa"},
        {"Who invented the light bulb?", "Nikola Tesla", "Thomas Edison", "Michael Faraday", "James Watt", "Thomas Edison"},
        {"Which is the largest desert in the world?", "Sahara", "Gobi", "Kalahari", "Arctic", "Sahara"},
        {"What is the main language spoken in Brazil?", "Spanish", "Portuguese", "English", "French", "Portuguese"},
        {"How many continents are there?", "5", "6", "7", "8", "7"},
        {"Which is the fastest bird?", "Eagle", "Peregrine Falcon", "Ostrich", "Sparrow", "Peregrine Falcon"},
        {"What is the main ingredient in bread?", "Rice", "Flour", "Corn", "Oats", "Flour"},
        {"Which country hosted the 2016 Olympics?", "China", "Brazil", "UK", "Japan", "Brazil"},
        {"What is the capital of Germany?", "Munich", "Berlin", "Frankfurt", "Hamburg", "Berlin"},
        {"What is 15 × 6?", "80", "85", "90", "95", "90"},
        {"What is the largest island in the world?", "Greenland", "Madagascar", "Borneo", "Australia", "Greenland"},
        {"Which planet is known for its rings?", "Mars", "Venus", "Saturn", "Neptune", "Saturn"},
        {"How many sides does a hexagon have?", "5", "6", "7", "8", "6"},
        {"Which part of the plant conducts photosynthesis?", "Root", "Leaf", "Stem", "Flower", "Leaf"},
        {"Who was the first man on the Moon?", "Buzz Aldrin", "Yuri Gagarin", "Neil Armstrong", "Michael Collins", "Neil Armstrong"},
        {"Which sea separates Europe and Africa?", "Red Sea", "Mediterranean Sea", "Black Sea", "Caspian Sea", "Mediterranean Sea"},
        {"What is the smallest unit of life?", "Tissue", "Cell", "Organ", "Molecule", "Cell"},
        {"Which animal is known as the King of the Jungle?", "Tiger", "Lion", "Leopard", "Cheetah", "Lion"},
        {"Which vitamin is produced when the skin is exposed to sunlight?", "Vitamin A", "Vitamin B", "Vitamin C", "Vitamin D", "Vitamin D"},
        {"What is the currency of the USA?", "Dollar", "Pound", "Euro", "Peso", "Dollar"},
        {"Which is the largest type of big cat?", "Lion", "Tiger", "Leopard", "Jaguar", "Tiger"},
        {"What is the main gas in the Sun?", "Oxygen", "Hydrogen", "Nitrogen", "Helium", "Hydrogen"},
        {"What is the study of fossils called?", "Biology", "Geology", "Paleontology", "Ecology", "Paleontology"},
        {"How many colors are in a rainbow?", "5", "6", "7", "8", "7"},
        {"Which is the largest planet?", "Earth", "Jupiter", "Saturn", "Neptune", "Jupiter"},
        {"Which bird can mimic human speech?", "Crow", "Parrot", "Owl", "Pigeon", "Parrot"},
        {"What is the capital of Russia?", "Moscow", "St Petersburg", "Kiev", "Sochi", "Moscow"},
        {"How many teeth does an adult human have?", "28", "30", "32", "34", "32"},
        {"Which organ is responsible for pumping blood?", "Brain", "Heart", "Lungs", "Kidneys", "Heart"},
        {"Which is the slowest animal?", "Snail", "Sloth", "Turtle", "Koala", "Sloth"},
        {"What is the chemical symbol for sodium?", "So", "Na", "S", "Sn", "Na"},
        {"Which is the hottest planet in the solar system?", "Mercury", "Venus", "Mars", "Jupiter", "Venus"},
        {"What is the national flower of India?", "Lotus", "Rose", "Marigold", "Sunflower", "Lotus"},
        {"What is the square of 13?", "156", "169", "144", "196", "169"},
        {"Which country gifted the Statue of Liberty to the USA?", "France", "UK", "Germany", "Canada", "France"},
        {"What is the capital of China?", "Beijing", "Shanghai", "Hong Kong", "Guangzhou", "Beijing"},
        {"Which planet is called Earth's twin?", "Venus", "Mars", "Mercury", "Saturn", "Venus"},
        {"Which gas is used in balloons?", "Oxygen", "Hydrogen", "Helium", "Nitrogen", "Helium"},
        {"What is 100 divided by 4?", "20", "25", "30", "40", "25"},
        {"Which country is known for kangaroos?", "Australia", "New Zealand", "South Africa", "India", "Australia"},
        {"What is the national sport of Japan?", "Sumo Wrestling", "Karate", "Baseball", "Judo", "Sumo Wrestling"},
        {"How many legs does a spider have?", "6", "8", "10", "12", "8"},
        {"Which instrument is used to measure temperature?", "Thermometer", "Barometer", "Hygrometer", "Anemometer", "Thermometer"},
        {"What is the capital of Italy?", "Rome", "Venice", "Florence", "Milan", "Rome"},
        {"Which month has 28 or 29 days?", "January", "February", "March", "April", "February"},
        {"What is the chemical symbol for iron?", "Ir", "In", "Fe", "I", "Fe"},
        {"What is the process of water turning into vapor called?", "Evaporation", "Condensation", "Precipitation", "Freezing", "Evaporation"},
        {"Which organ helps you breathe?", "Liver", "Lungs", "Kidneys", "Brain", "Lungs"}
    };

    // Fill-in-the-Blank Questions: {Question, "", "", "", "", Answer}
    private static String fillQuestions[][] = {
    {"The SI unit of force is ____.", "", "", "", "", "NEWTON"},
    {"The layer of the Earth where earthquakes occur is the ____.", "", "", "", "", "CRUST"},
    {"The chemical symbol for potassium is ____.", "", "", "", "", "K"},
    {"The country with the largest population in the world is ____.", "", "", "", "", "INDIA"},
    {"The three primary colors of light are red, green, and ____.", "", "", "", "", "BLUE"},
};


    public static String[][] getRandomQuestions(int num) {
        if (num != 10) throw new IllegalArgumentException("This quiz supports exactly 10 questions (7 MCQ + 3 FITB).");

        Random rand = new Random();
        List<String[]> selected = new ArrayList<>();

        List<String[]> mcqList = new ArrayList<>(Arrays.asList(mcqQuestions));
        Collections.shuffle(mcqList, rand);
        selected.addAll(mcqList.subList(0, 7));

        List<String[]> fitbList = new ArrayList<>(Arrays.asList(fillQuestions));
        Collections.shuffle(fitbList, rand);
        selected.addAll(fitbList.subList(0, 3));

        Collections.shuffle(selected, rand);

        return selected.toArray(new String[10][6]);
    }
}
