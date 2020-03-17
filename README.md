# LS-Solver
linear systems of algebraic equation using iterative methods

  
        // Type Numbers only completed
        textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if( ! (Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE)) { 
					// if not a back_space button or del button or not a digit -> not typing and make a warn sound
					e.consume(); 
					// Addition -->  make beep (warn) sound :)
					getToolkit().beep();
				}
	
			}
		});
