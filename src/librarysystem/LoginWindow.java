package librarysystem;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

import javax.swing.*;
import java.awt.*;
import java.util.Map;


public class LoginWindow extends JPanel implements LibWindow {
    public static final LoginWindow INSTANCE = new LoginWindow();
	
	private boolean isInitialized = false;
	private JTextField username;
	private JPasswordField password;
	
	public void init() {
		clearContent();
        JLabel loginLabel = new JLabel("Sign In:");
        Util.adjustLabelFont(loginLabel, Color.BLUE.darker(), true);
        add(loginLabel);
        JSeparator s = new JSeparator();
        s.setOrientation(SwingConstants.HORIZONTAL);
        add(s);
        username = new JTextField(45);
        username.setMaximumSize(username.getPreferredSize());
        JLabel uLabel = new JLabel("Username:");
        uLabel.setFont(Util.makeSmallFont(uLabel.getFont()));
        add(uLabel);
        add(username);
        password = new JPasswordField(45);
        password.setMaximumSize(password.getPreferredSize());
        JLabel pLabel = new JLabel("Password:");
        pLabel.setFont(Util.makeSmallFont(pLabel.getFont()));
        add(pLabel);
        add(password);
        JButton loginButton = new JButton("Sign In");
        addLoginButtonListener(loginButton);
        add(loginButton);
        setAlignmentY(Component.LEFT_ALIGNMENT);
        isInitialized(true);
    }
	
	public void clearContent() {
		removeAll();
        revalidate();
        repaint();
	}
	
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	/* This class is a singleton */
    private LoginWindow () {
    	init();
    }
    	
    private void addLoginButtonListener(JButton butn) {
    	butn.addActionListener(evt -> {
	    	String userId = username.getText();
	    	String userPass = password.getText();
	    	if(userId == null || userId.isBlank() || userPass == null || userPass.isBlank()) {
	    		JOptionPane.showMessageDialog(this,"Invalid username or password", "", JOptionPane.ERROR_MESSAGE);
	    		return;
	    	}

			ControllerInterface systemController = new SystemController();
			Map<String, User> users;

			try {
				systemController.login(userId, userPass);
				users = systemController.userMap();
				User user = users.get(userId);
				username.setText("");
				password.setText("");
				System.out.println(user.getAuthorization());
				LibrarySystem.INSTANCE.setLoggedInUser(user);
				LibrarySystem.INSTANCE.init();
			} catch (LoginException e) {

				JOptionPane.showMessageDialog(this,e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
//				throw new RuntimeException(e);
				return;
			}

//
//			DataAccess da = new DataAccessFacade();
//	    	Map<String, User> users = da.readUserMap();
//	    	if(!users.containsKey(userId)) {
//	    		JOptionPane.showMessageDialog(this,"Invalid username", "", JOptionPane.ERROR_MESSAGE);
//	    		return;
//	    	}
//	    	User user = users.get(userId);
//	    	if(!userPass.equals(user.getPassword())) {
//	    		JOptionPane.showMessageDialog(this,"Invalid password", "", JOptionPane.ERROR_MESSAGE);
//	    		return;
//	    	}
//	    	username.setText("");
//	    	password.setText("");
//	    	System.out.println(user.getAuthorization());
//	    	LibrarySystem.INSTANCE.setLoggedInUser(user);
//	    	LibrarySystem.INSTANCE.init();
	  	});
    }
}
