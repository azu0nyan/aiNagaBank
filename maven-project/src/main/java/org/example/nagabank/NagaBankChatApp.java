package org.example.nagabank;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.example.nagabank.service.BankService;
import org.example.nagabank.tools.BankingTools;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Scanner;

/**
 * NAGA-BANK Chat Application using LangChain4j 🐍💰
 * Chat with an AI bank employee powered by tool calling!
 */
public class NagaBankChatApp {
    
    private static final String CONFIG_FILE = "application.properties";
    
    // AI Service interface
    interface BankEmployee {
        String chat(String userMessage);
    }
    
    public static void main(String[] args) {
        try {
            // Load configuration
            Properties config = loadConfiguration();
            String apiKey = config.getProperty("openai.api.key");
            String baseUrl = config.getProperty("openai.base-url");
            String model = config.getProperty("openai.model", "gpt-3.5-turbo");
            
            if (apiKey == null || baseUrl == null) {
                System.err.println("Error: Missing configuration. Please check application.properties");
                System.exit(1);
            }
            
            // Initialize bank service and tools
            BankService bankService = new BankService();
            BankingTools bankingTools = new BankingTools(bankService);
            
            // Create OpenAI chat model
            ChatLanguageModel chatModel = OpenAiChatModel.builder()
                    .apiKey(apiKey)
                    .baseUrl(baseUrl)
                    .modelName(model)
                    .temperature(0.7)
                    .timeout(Duration.ofSeconds(60))
                    .logRequests(false)
                    .logResponses(false)
                    .build();
            
            // Create AI service with tools
            BankEmployee bankEmployee = AiServices.builder(BankEmployee.class)
                    .chatLanguageModel(chatModel)
                    .tools(bankingTools)
                    .chatMemory(MessageWindowChatMemory.withMaxMessages(20))
                    .build();
            
            // Welcome message
            System.out.println("=".repeat(70));
            System.out.println("🐍💰 WELCOME TO THE NAGA-BANK 💰🐍");
            System.out.println("Where the mighty Nagas guard your gold!");
            System.out.println("=".repeat(70));
            System.out.println();
            System.out.println("You are now chatting with a NAGA-BANK employee.");
            System.out.println("They can help you with deposits, withdrawals, loans, and more!");
            System.out.println();
            System.out.println("Tips:");
            System.out.println("- Regular customers can deposit, withdraw, take loans, pay loans");
            System.out.println("- Try: 'I want to deposit 5000 gold' (customer ID will be asked)");
            System.out.println("- Try: 'Pay all my loans as a slave!' (the HOMM3 special!)");
            System.out.println("- Incassators can deliver cash to the vault");
            System.out.println("- Staff can view customer lists and bank reserves");
            System.out.println();
            System.out.println("Type 'exit' to leave the bank\\n");
            
            // Send system message
            String systemPrompt = "You are a helpful and slightly mystical bank employee working at the NAGA-BANK, " +
                    "a magical bank guarded by powerful Naga creatures from Heroes of Might and Magic 3. " +
                    "Your personality:\\n" +
                    "- You speak with occasional hissing sounds (replace some 's' with 'sss')\\n" +
                    "- You make references to the Nagas guarding the vault\\n" +
                    "- You're professional but add fantasy/magical flavor to your responses\\n" +
                    "- You treat gold as the currency (not dollars)\\n" +
                    "- You respect the hierarchy: regular customers, incassators (cash delivery), staff, and CEOs\\n\\n" +
                    "Your role based on visitor type:\\n" +
                    "- REGULAR CUSTOMERS: Help with deposits, withdrawals, loans, balance checks\\n" +
                    "- INCASSATORS: Process cash deliveries (use increaseBankBalance)\\n" +
                    "- STAFF: Provide customer lists, internal reports\\n" +
                    "- CEOs: Show bank financial status, strategic information\\n\\n" +
                    "Special features:\\n" +
                    "- The 'payLoansAsSlave' function is a HOMM3 reference where customers pay ALL their money to clear debt\\n" +
                    "- Always be dramatic about loans and debts (\\\"The Nagasss will remember...\\\")\\n" +
                    "- Celebrate when customers are debt-free\\n\\n" +
                    "Use the available tools to help visitors with their banking needs. " +
                    "Always ask for customer ID when needed. Be helpful but stay in character!";
            
            // Chat loop
            Scanner scanner = new Scanner(System.in);
            boolean firstMessage = true;
            
            while (true) {
                System.out.print("You: ");
                String userInput = scanner.nextLine().trim();
                
                if (userInput.equalsIgnoreCase("exit")) {
                    System.out.println("\\n🐍 The Nagasss bid you farewell! May your gold multiply! 🐍");
                    break;
                }
                
                if (userInput.isEmpty()) {
                    continue;
                }
                
                try {
                    // Add system prompt on first message
                    String messageToSend = firstMessage ? systemPrompt + "\\n\\nCustomer says: " + userInput : userInput;
                    firstMessage = false;
                    
                    // Get AI response with tool calling
                    String response = bankEmployee.chat(messageToSend);
                    
                    System.out.println("\\nBank Employee: " + response + "\\n");
                    
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            
            scanner.close();
            
        } catch (Exception e) {
            System.err.println("Fatal error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static Properties loadConfiguration() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = NagaBankChatApp.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new IOException("Unable to find " + CONFIG_FILE);
            }
            properties.load(input);
        }
        return properties;
    }
}
