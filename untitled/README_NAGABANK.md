# 🐍💰 NAGA-BANK - Heroes of Might and Magic 3 Banking System 💰🐍

A magical banking system inspired by Heroes of Might and Magic 3, powered by **LangChain4j** AI with function calling!

## 🎮 HOMM3 Theme

The NAGA-BANK is guarded by powerful Naga creatures who watch over your gold! Features include:
- **Naga-themed responses** with hissing sounds (sss)
- **"Pay as Slave" option** - trade ALL your gold to clear debts (HOMM3 reference)
- **Gold currency** instead of dollars
- **Mystical atmosphere** with dragon guards

## 🏗️ Architecture

### Package Structure
```
org.example.nagabank/
├── model/              # Data models
│   ├── Customer.java
│   ├── CustomerData.java
│   └── CustomerType.java
├── service/            # Business logic
│   └── BankService.java
├── tools/              # LangChain4j AI tools
│   └── BankingTools.java
└── NagaBankChatApp.java  # Main application
```

### Banking Features
1. **Customer Operations**
   - Deposit gold
   - Withdraw gold
   - Take loans (decreases bank reserves)
   - Pay back loans
   - Pay ALL loans as a slave (special HOMM3 feature!)
   - Check balance
   - Check loan information

2. **Staff Operations**
   - View customer list with balances and loans
   - Monitor bank operations

3. **Incassator Operations**
   - Deliver cash to bank vault (increase reserves)

4. **CEO Operations**
   - View bank financial status
   - Check total reserves
   - Strategic oversight

## 🚀 How to Run

### 1. Configure OpenRouter API
Edit `src/main/resources/application.properties`:
```properties
openai.api.key=YOUR_OPENROUTER_API_KEY
openai.base-url=https://openrouter.ai/api/v1
openai.model=anthropic/claude-3.5-sonnet
```

Get your API key from: https://openrouter.ai/

### 2. Compile the Project
```bash
mvn clean compile
```

### 3. Run the Chat Application
```bash
mvn exec:java -Dexec.mainClass="org.example.nagabank.NagaBankChatApp"
```

### 4. Chat with the Bank Employee!
```
🐍💰 WELCOME TO THE NAGA-BANK 💰🐍
Where the mighty Nagas guard your gold!

You: I want to deposit 5000 gold
Bank Employee: Excellent! The Nagasss welcome your gold...

You: Check my balance
Bank Employee: Your current balance issss...

You: I need a loan of 2000 gold
Bank Employee: The Naga Council has approved your loan...

You: Pay all my loans as a slave!
Bank Employee: You have chosen the path of the debt slave...
```

## 🛠️ Technology Stack

- **Java 11** - Core language
- **Maven** - Build tool
- **LangChain4j 0.35.0** - AI framework with tool calling
  - `langchain4j` - Core library
  - `langchain4j-open-ai` - OpenAI-compatible API support
- **OpenRouter** - AI API gateway (supports Claude, GPT-4, etc.)

## 💡 Key Features

### LangChain4j Tool Calling
The `BankingTools` class uses `@Tool` annotations to automatically expose methods to the AI:

```java
@Tool("Deposit money into a customer's account")
public String deposit(String customerId, double amount) {
    return bankService.deposit(customerId, amount);
}
```

The AI automatically:
- Detects which tool to call based on user intent
- Extracts parameters from natural language
- Executes the tool and returns results

### AI Service Interface
Simple interface definition:
```java
interface BankEmployee {
    String chat(String userMessage);
}
```

LangChain4j generates the implementation with tool calling built-in!

### In-Memory Storage
Uses `HashMap` for customer data storage:
- Fast lookups
- No database required
- Perfect for demos

## 📝 Example Conversations

### Deposit Gold
```
You: Hey, I'm Alice and I want to deposit 5000 gold
Employee: Excellent! The Nagasss welcome your contribution, Alice! 
          5000.0 gold has been deposited. Your new balance: 5000.0 gold.
```

### Take a Loan
```
You: Can I get a loan of 2000 gold?
Employee: The Naga Council has approved your loan request! 
          2000.0 gold has been granted. Remember, the Nagasss always collect...
```

### Pay as Slave (HOMM3 Special!)
```
You: I want to pay all my loans as a slave!
Employee: You have chosen the path of the debt slave! 
          ALL your gold (3000.0) has been offered to the Nagas to clear your debt of 2000.0. 
          The Nagas accept your sacrifice!
```

## 🔧 Customization

### Change AI Model
Edit `application.properties`:
```properties
# Use different models via OpenRouter
openai.model=anthropic/claude-3.5-sonnet      # Best quality
openai.model=openai/gpt-4-turbo               # GPT-4
openai.model=meta-llama/llama-3.1-70b-instruct  # Open source
```

### Modify Bank Employee Personality
Edit the system prompt in `NagaBankChatApp.java` to change:
- Speaking style
- References and jokes
- Professional tone
- Fantasy elements

### Add New Banking Features
1. Add method to `BankService.java`
2. Add `@Tool` annotated method to `BankingTools.java`
3. The AI automatically learns the new feature!

## 🎭 Customer Types

The system recognizes different visitor types:
- **CUSTOMER** - Regular banking operations
- **INCASSATOR** - Cash delivery personnel
- **STAFF** - Internal employees
- **CEO** - Executive oversight

The AI employee adjusts responses based on visitor type!

## 🧪 Test the Banking Logic

Run the unit test to see all operations:
```bash
mvn exec:java -Dexec.mainClass="org.example.TestNagaBank"
```

This demonstrates all banking operations without AI interaction.

## 🎨 HOMM3 References

- **Nagas** - The mystical guards from HOMM3
- **Gold** - The primary currency  
- **Debt Slavery** - Paying all resources to clear debt (game mechanic)
- **Dragon Guards** - Protectors of the vault
- **Hissing Sounds** - Naga character trait (sss)

## 📚 Additional Resources

- [LangChain4j Documentation](https://docs.langchain4j.dev/)
- [OpenRouter API](https://openrouter.ai/docs)
- [Heroes of Might and Magic 3](https://en.wikipedia.org/wiki/Heroes_of_Might_and_Magic_III)

## 🐛 Troubleshooting

### API Key Issues
- Ensure your OpenRouter API key is valid
- Check that you have credits on OpenRouter
- Verify the base URL is correct

### Compilation Errors
- Make sure Java 11+ is installed
- Run `mvn clean install` to refresh dependencies

### Model Not Responding
- Try a different model in `application.properties`
- Check OpenRouter status page
- Verify internet connection

## 🎉 Have Fun!

Enjoy chatting with your AI bank employee and may the Nagas guard your gold well!

*"The Nagasss are waiting for you..."* 🐍💰
