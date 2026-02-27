import java.util.ArrayList;
import java.util.List;

public class PharmacyDatabase {
    // Shared inventory list accessible by all windows
    public static List<Item> inventory = new ArrayList<>();

    // Static block to preload some medicines
    static {
        inventory.add(new Item("Paracetamol", 50, 20.0, "01-01-2025", "01-01-2026"));
        inventory.add(new Item("Amoxicillin", 100, 45.0, "15-03-2024", "15-03-2027"));
        inventory.add(new Item("Cough Syrup", 120, 80.0, "10-04-2024", "10-04-2027"));
        inventory.add(new Item("Vitamin C", 200, 20.0, "20-02-2024", "20-02-2026"));
        inventory.add(new Item("Ibuprofen", 80, 45.0, "05-05-2025", "05-05-2028"));
        inventory.add(new Item("Cetrizine", 30, 30.0 , "25-06-2024", "25-06-2026"));
        inventory.add(new Item("Combiflame", 30, 60.0 , "25-06-2024", "25-06-2027"));
        inventory.add(new Item("Saridon", 30, 40.0 , "25-06-2024", "25-06-2026"));
        inventory.add(new Item("Disprin", 30, 12.0 , "25-06-2024", "25-06-2026"));
        inventory.add(new Item("Avil50", 30, 35.0 ,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Naproxen", 30, 40.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Vitamin C", 30, 20.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Lopex", 30, 25.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Liv-52", 30, 90.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Wegovy-injection", 30, 50.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Vicks", 30, 25.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Iodex", 30, 45.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Vicks Action 500", 30, 55.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Rabzole-DSR", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Rockcid-DSR", 30, 85.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Mulrab-DSR ", 30, 75.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Pantocid-DSR ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Vitamin E ", 30, 15.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Diclofenac", 30, 90.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Doxycycline ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Atenolol ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Ceftriaxone injection", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Fluoxetine", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Gentamicin eye drops ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Metformin", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Ranitidine ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Simvastatin", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Velax SR", 30, 78.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("NICOTIN", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Rotavirus vaccine ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Limci", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Gabavent-300 ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("becosules", 30, 60.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Mebizid-C ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Ranitidine", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Latanoprost ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Amikacin ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Ivermectin ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Amlodipine ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Levocetirizine ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Prednisolone ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Dexamethasone ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Pregabalin", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Promethazine ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Protonix ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Quetiapine ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Risperdal ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Ritalin ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Sudafed ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Sulfamethoxazo ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Crocin ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("COZYMIN ", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Mahamox-CV 625", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("I-pill", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Norethisterone", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Vigra", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("ZENFLOX-UTI", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Dettol Antiseptic", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Otrivin Oxy Fast Relief", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Saridon Advance", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Dabur Shilajit Gold", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("BOROLINE", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Ashwagandha", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Revital Women", 30, 95.0,"01-01-2025" ,"01-01-2028"));
        inventory.add(new Item("Hidroeyes-Eye drops", 50, 365.0,"01-01-2026" ,"01-01-2028"));
        inventory.add(new Item("Refresh Tears-Eye drops", 50, 252.0,"01-01-2026" ,"01-01-2028"));
        inventory.add(new Item("BePos-Eye drops", 50, 360.0,"01-01-2026" ,"01-01-2028"));
        inventory.add(new Item("LotelGel-Eye drops", 50, 429.0,"01-01-2026" ,"01-01-2028"));

        // Add more items as needed
    }
} 

