package com.java.SpringDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDemoApplication {

		public static void main(String[] args) {
			// Start the Spring Boot application
			SpringApplication.run(SpringDemoApplication.class);
//			SpringApplicationBuilder builder = new SpringApplicationBuilder(SpringDemoApplication.class);
//
//			// This line tells Spring that a GUI (AWT/Swing) will be used
//			builder.headless(false);
//
//			// Optional: If you aren't running a web server (like Tomcat), set this to NONE
//			builder.web(WebApplicationType.NONE);
//
//			builder.run(args);
		}

//		// This Bean runs after Spring starts up to launch the GUI
//		@Bean
//		CommandLineRunner startGui(ChatModel chatModel) {
//			return args -> {
//				SwingUtilities.invokeLater(() -> {
//					AiChatFrame view = new AiChatFrame();
//					GeminiChatController controller = new GeminiChatController(chatModel);
//
//					view.getSendButton().addActionListener(e -> {
//						String prompt = view.getPromptField().getText();
//						if (prompt.isBlank()) return;
//
//						view.getResponseArea().append("You: " + prompt + "\n\n");
//						view.getPromptField().setText("");
//						view.getSendButton().setEnabled(false);
//
//						// Use SwingWorker to keep UI responsive
//						new SwingWorker<String, Void>() {
//							@Override
//							protected String doInBackground() {
//								return controller.chat(new GeminiChatController.InputGemini(prompt)).content();
//							}
//
//							@Override
//							protected void done() {
//								try {
//									String response = get();
//									view.getResponseArea().append("AI: " + response + "\n\n");
//								} catch (Exception ex) {
//									view.getResponseArea().append("AI Error: " + ex.getMessage() + "\n\n");
//								} finally {
//									view.getSendButton().setEnabled(true);
//								}
//							}
//						}.execute();
//					});
//
//					view.setVisible(true);
//				});
//			};
//		}
//	}
//
//	/**
//	 * The GUI Class (The View)
//	 */
//	class AiChatFrame extends JFrame {
//		private final JTextField promptField;
//		private final JTextArea responseArea;
//		private final JButton sendButton;
//
//		public AiChatFrame() {
//			setTitle("Gemini");
//			setSize(600, 500);
//			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			setLocationRelativeTo(null);
//			setLayout(new BorderLayout(10, 10));
//
//			// Output Area
//			responseArea = new JTextArea();
//			responseArea.setEditable(false);
//			responseArea.setLineWrap(true);
//			responseArea.setWrapStyleWord(true);
//			responseArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
//
//			JScrollPane scrollPane = new JScrollPane(responseArea);
//			scrollPane.setBorder(BorderFactory.createTitledBorder("AI Response"));
//
//			// Input Panel
//			JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
//			promptField = new JTextField();
//			sendButton = new JButton("Send");
//
//			inputPanel.add(new JLabel(" Prompt: "), BorderLayout.WEST);
//			inputPanel.add(promptField, BorderLayout.CENTER);
//			inputPanel.add(sendButton, BorderLayout.EAST);
//
//			add(scrollPane, BorderLayout.CENTER);
//			add(inputPanel, BorderLayout.SOUTH);
//		}
//
//		public JTextField getPromptField() { return promptField; }
//		public JTextArea getResponseArea() { return responseArea; }
//		public JButton getSendButton() { return sendButton; }
	}