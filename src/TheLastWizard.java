import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.backends.lwjgl.audio.Mp3.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import java.util.Random;
public class TheLastWizard extends Game {
	// *MUST* click 'escape' to exit game to avoid errors, but they're not game-breaking
	/*
	Credits:
	Game Art:
	OpenGameArt
	SpriteLib
	HasGrahpics
	
	Music/Sound:
	FreeSound.org
	https://www.youtube.com/watch?v=IHI6bfFIUCk
	*/
	// Renders frame by frame, creates the game, etc.
	// To determine gameState
	boolean firstRun = true;
	boolean instructionMenu = false;
	boolean instructionMenu2 = false;
	boolean wasMenuButtonDown = false;
	boolean gameOver = false;
	int timerBeforeCrash = 300;
		
	// miscellaneous
	Random ranNum = new Random();
    int frameSkipper = 0;
    boolean wasFireballKeyUnpressed = true;
    boolean wasFireWaveKeyUnpressed = true;
    boolean wasIceCometKeyUnpressed = true;
    boolean wasvoidGateKeyUnpressed = true;
    
	// Sprites
	private SpriteBatch batch;
    private Sprite backgroundImage;
    private Sprite blackBackground;
    private Sprite burningCity;
    private Sprite instructionsOne;
    private Sprite wizardImage;
    private Sprite wolfImage;
    private Sprite goblinImage;
    private Sprite darknessKnightImage;
    private Sprite scarecrowImage;
    private Sprite ghostImage;
    private Sprite deathEaterImage;
    private Sprite fireWhipCDBarOutline;
    private Sprite fireWhipCooldownBar;
    private Sprite enemyHealthBar;
    private Sprite manaBarOutline;
    private Sprite manaBar;
    private Sprite fireWhipImg;
    private Sprite fireballImg;
    private Sprite ultBarOutline;
    private Sprite ultBar;
    private Sprite fireWaveImg;
    private Sprite iceballImg;
    private Sprite voidGateImg;
    private Sprite playerHealthImg;
    private Sprite menuLandscape;
    private Sprite theLast;
    private Sprite wizardScreen;
    private Sprite play;
    private Sprite instructions;
    private Sprite instructionsTwo;
    private Sprite gameOverImage;
    
    // arrayLists
    Enemies enemyList = new Enemies(); // Array list containing all enemies on screen
    Spells spellList = new Spells(); // Array list containing all spells on screen
    
    // General
    float wizardX = 125; // Location of wizard
    float wizardY = 20;
    int HPBarHeight = 20;    
    int playerMana = 100;
    int playerUltMana = 1000;
    int playerHP = 6;
    
    // Spell fireWhip (general gameplay mechanics & Parameters for the fireWhip Spell constructor)
    int fireWhipCooldown = 0; // Used for the cooldown and overheat of the fire whip spell
    boolean canIUseFireWhip = true;
    int maxTemperature = 150;
    int fireWhipSpd = 4;
    double fireWhipDmg = 1;
    int fireWhipInitX = 130;
    int fireWhipInitY = 25;
    int fireWhipSpriteWidth = 5;
    int fireWhipSpriteHeight = 10;
    
    // Spell fireball stats & values
    int fireballSpd = 6;
    double fireballDmg = 10;
    int fireballInitX = 0; // no set initial 'x' position
    int fireballInitY = 0;
    int fireballSpriteWidth = 50;
    int fireballSpriteHeight = 100;
    
    // Spell iceball stats & values
    int iceballSpd = 6;
    double iceballDmg = 0.25; // this ability "pierces" enemies, so 0.25damage/tick of Rectangle overlap
    int iceballInitX = 0; // no set initial 'x' position
    int iceballInitY = 0;
    int iceballSpriteWidth = 25;
    int iceballSpriteHeight = 50;
    
    // Spell voidGate stats & values
    int voidGateSpd = 6;
    double voidGateDmg = 3; 
    int voidGateInitX = 0; // no set initial 'x' position
    int voidGateInitY = 0;
    int voidGateSpriteWidth = 25;
    int voidGateSpriteHeight = 50;
    int voidGatePushbackAmount = 200; // Number of pixels voidGate moves back the enemy
   
    // Spell fireWave stats & values
    int fireWaveSpeed = 5;
    double fireWaveDmg = 2; 
    int fireWaveInitX = 100;
    int fireWaveInitY = -20;
    int fireWaveSpriteWidth = 300;
    int fireWaveSpriteHeight = 150;
    
    
    Music menuMusic; // Background music
    com.badlogic.gdx.audio.Sound fireballSound;
    com.badlogic.gdx.audio.Sound spookySound;
    
    
    public void create() // Initially prints the wizard and background
    {        
        batch = new SpriteBatch();
        wizardImage = new Sprite( new Texture(Gdx.files.internal("assets/wizard.png")) );
        backgroundImage = new Sprite( new Texture(Gdx.files.internal("assets/gameBackgroundFinal.png")) );
        burningCity = new Sprite( new Texture(Gdx.files.internal("assets/burningCity.jpg")) );
        instructionsOne = new Sprite( new Texture(Gdx.files.internal("assets/instructionsPageOne.png")) );
        instructionsTwo = new Sprite( new Texture(Gdx.files.internal("assets/instructionsPageTwoFinal.png")) );
		wolfImage = new Sprite( new Texture(Gdx.files.internal("assets/wolf.png")) ); 
		goblinImage = new Sprite( new Texture(Gdx.files.internal("assets/goblin.png")) ); 
		darknessKnightImage = new Sprite( new Texture(Gdx.files.internal("assets/darknessKnight.png")) );
		scarecrowImage = new Sprite( new Texture(Gdx.files.internal("assets/scarecrow.png")) );
		ghostImage = new Sprite( new Texture(Gdx.files.internal("assets/ghost.png")) );
		deathEaterImage = new Sprite( new Texture(Gdx.files.internal("assets/awesome wizard.png")) );
		fireWhipCDBarOutline = new Sprite( new Texture(Gdx.files.internal("assets/GreenBar.png")) );
		fireWhipCooldownBar = new Sprite( new Texture(Gdx.files.internal("assets/greenBarFiller.png")) );
		enemyHealthBar = new Sprite( new Texture(Gdx.files.internal("assets/healthBar.png")) );
		manaBarOutline = new Sprite( new Texture(Gdx.files.internal("assets/bluebaroutline.png")) );
		manaBar = new Sprite( new Texture(Gdx.files.internal("assets/bluebar.png")) );
		fireWhipImg = new Sprite( new Texture(Gdx.files.internal("assets/fireWhip.png")) );
		fireballImg = new Sprite( new Texture(Gdx.files.internal("assets/fireball.png")) );
		ultBarOutline = new Sprite( new Texture(Gdx.files.internal("assets/blackbaroutline.png")) );
		ultBar = new Sprite( new Texture(Gdx.files.internal("assets/blackbarfiller.png")) );
		fireWaveImg = new Sprite( new Texture(Gdx.files.internal("assets/firecrest.png")) );
		iceballImg = new Sprite( new Texture(Gdx.files.internal("assets/iceball.png")) );
		voidGateImg = new Sprite( new Texture(Gdx.files.internal("assets/voidball.png")) );
		playerHealthImg = new Sprite( new Texture(Gdx.files.internal("assets/6HP.png")) );
		menuLandscape = new Sprite( new Texture(Gdx.files.internal("assets/LastWizardLandscape.jpg")) );
		theLast = new Sprite( new Texture(Gdx.files.internal("assets/The-Last.png")) );
		wizardScreen = new Sprite( new Texture(Gdx.files.internal("assets/WizardScreen.png")) );
		play = new Sprite( new Texture(Gdx.files.internal("assets/beginGame.png")) );
		instructions = new Sprite( new Texture(Gdx.files.internal("assets/QuestionMark.png")) );
		gameOverImage = new Sprite( new Texture(Gdx.files.internal("assets/Game-Over.png")) );
		blackBackground = new Sprite( new Texture(Gdx.files.internal("assets/blackBackground.jpg")) );


		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("musicAndSounds/SSSMusic.wav")); // Initializing, looping and playing background music
		menuMusic.setLooping(true);
		float gameVolume = menuMusic.getVolume();
		float newGameVolume = gameVolume / 2; // Lowering music volume
		menuMusic.setVolume(newGameVolume);
		menuMusic.play();
		
    	fireballSound = Gdx.audio.newSound(Gdx.files.internal("musicAndSounds/fireballSound.mp3"));
    	spookySound = Gdx.audio.newSound(Gdx.files.internal("musicAndSounds/spooky.mp3"));


        // Image for goblin and darkness knight are from gameartpartners.com
    }

    public void render() 
    {      
        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) { // If player wants to quit game, we must dispose music first
        	menuMusic.dispose();
        	fireballSound.dispose();
        	spookySound.dispose();
        	Gdx.app.exit();
        }
        
        if (gameOver == true){
    		batch.begin();
    		
        	batch.draw(burningCity, -75, 0, 400, 480 ); // Game over screen
        	batch.draw(blackBackground, -20, 320, 400, 15 );
        	batch.draw(gameOverImage, 10, 320, 250, 60 );

    		batch.end();
    		timerBeforeCrash--;

    		if (timerBeforeCrash == 200){
	        	spookySound.play(1.0f);
    		}
    		
    		if (timerBeforeCrash == 0){
    			menuMusic.dispose();
            	fireballSound.dispose();
            	spookySound.dispose();
            	Gdx.app.exit();
    		}
    	}
        
        else if (gameOver == false){
	    	if (firstRun == true){ // If the user has not yet selected to play or view instructions (menu display); Only upon launch
	    		if (instructionMenu == false){ // Main menu
	    			batch.begin(); // For batch.draw calls, when seemingly "random" numbers are used as parameters, those are just to position the image based on guess&check...
		        	batch.draw(menuLandscape, 0, 0, 960, 480 );
		        	batch.draw(theLast, 36, 400, 200, 38 );
		        	batch.draw(wizardScreen, 10, 320, 250, 84 );
		        	batch.draw(play, 175, 10, 50, 50 );
		        	batch.draw(wizardImage, 110, 10, 50, 60 );
		        	batch.draw(instructions, 50, 17, 34, 34 );
	    		}
	    		
	    		else if (instructionMenu == true && instructionMenu2 == false){ // Instructions page 1
	    			batch.begin();
	    			Gdx.gl.glClearColor(1, 1, 1, 1);
	    	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    	        batch.draw(instructionsOne, 0, 0, 270, 480 );
	    	        batch.draw(instructions, 50, 17, 34, 34 );
		        	batch.draw(play, 175, 10, 50, 50 );
	    		}
	    		
	    		else if (instructionMenu2 == true){ // Instructions page 2
	    			batch.begin();
	    			Gdx.gl.glClearColor(1, 1, 1, 1);
	    	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    	        batch.draw(instructionsTwo, 0, 0, 270, 480 );
		        	batch.draw(play, 175, 10, 50, 50 );
	    		}
	    		
	    		if (Gdx.input.isButtonPressed(Buttons.LEFT) && wasMenuButtonDown == false){ // If the user clicks, we will determine where it is
	    			wasMenuButtonDown = true;
	    			int xClick = Gdx.input.getX();
	    			int yClick = Gdx.input.getY();
	    			if (yClick > 426 && yClick < 466 && xClick > 182 && xClick < 218){ // If user clicks 'play' button. To determine these values, the x & y position of cursor was printed, and using that information it was easy to know where the edges of the button are
	    				firstRun = false;
	    			}
	    			if (yClick > 426 && yClick < 466 && xClick > 49 && xClick < 85){ // If user clicks 'instructions' button
	    				if (instructionMenu == false){
	    					instructionMenu = true;
	    				}
	    				else if (instructionMenu == true){ // If user clicks button for instructions pg.2
	    					instructionMenu2 = true;
	    				}
	    			}
	    		}
	    		
	    		if (!(Gdx.input.isButtonPressed(Buttons.LEFT))){ // Ensures that a mouse click is only registered once, not every frame
	    			wasMenuButtonDown = false;
	    		}
	    		
	    		batch.end();
	    	}
	    	
	    	if (firstRun == false){
		        Gdx.gl.glClearColor(1, 1, 1, 1);
		        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		        
		        // This section of code randomly spawns a new mob
		        int n = ranNum.nextInt(1400)+1; // Randomly generates a number, and if a specific number is generated, an enemy is spawned
		        if (n == 5 || n == 6 || n == 7 || n == 8){ 
		        	spawner("ghost");
		        }
		        else if (n == 100 || n == 101 || n == 102 || n == 103){ 
		        	spawner("wolf");
		        }
		        if (n == 200 || n == 220 || n == 240 || n == 260){
		        	spawner("goblin");
		        }
		        if (n == 300 || n == 320 || n == 340 || n == 380 || n == 390){
		        	spawner("scarecrow");
		        }
		        if (n == 400 || n == 420){
		        	spawner("darknessKnight");
		        }
		        else if (n == 480 || n == 490){
		        	spawner("deathEater");
		        }

		        // ------------------------------------------------------- 
		        // This section of code checks if the "a" key is being pressed, for the fireWhip spell.
		        
		        if (Gdx.input.isKeyPressed(Keys.A)) { 
		        	if (fireWhipCooldown <= maxTemperature && canIUseFireWhip){
		        		int mouseX = Gdx.input.getX();
		        		int mouseY = Gdx.input.getY();
		        		Spell fireWhip = new Spell("fireWhip", fireWhipSpd, fireWhipDmg, fireWhipImg, fireWhipInitX, fireWhipInitY, fireWhipSpriteWidth, fireWhipSpriteHeight, mouseX, mouseY); // new fireWhip spell object created every frame
		        		spellList.addSpell(fireWhip);
		        		fireWhipCooldown+=2;
		        	}
		        	else{
		        		canIUseFireWhip = false;
		        	}
		        }
		        // Code involving boolean canIUseFireWhip and int fireWhipCooldown tracks if the user is overheating the spell; this information is presented to the user as a green bar at the bottom of the screen
		        if (!(Gdx.input.isKeyPressed(Keys.A)) && fireWhipCooldown > 0) { 
		        	fireWhipCooldown --;
		        	if (fireWhipCooldown <= 20){
		        		canIUseFireWhip = true;
		        	}
		        }
		        
		        // -------------------------------------------------------
		        // This section of code checks if the "s" key is pressed, for the fireball spell
		        
		        if (Gdx.input.isKeyPressed(Keys.S) && wasFireballKeyUnpressed && playerMana >= 25) { // Fireball spell used if 's' key pressed and key was not pressed in the last frame
		        	int mouseX = Gdx.input.getX();
		    		int mouseY = Gdx.input.getY();
		        	Spell fireball = new Spell ("fireball", fireballSpd, fireballDmg, fireballImg, fireballInitX, fireballInitY, fireballSpriteWidth, fireballSpriteHeight, mouseX, mouseY);
		        	spellList.addSpell(fireball);
		        	wasFireballKeyUnpressed = false;
		        	playerMana-=25;
		        	fireballSound.play(1.0f);
		        }
		        if (!(Gdx.input.isKeyPressed(Keys.S))){
		        	wasFireballKeyUnpressed = true;
		        }
		        
		        // -------------------------------------------------------
		        // This section of code checks if the "D" key is pressed, for the iceball spell
		        
		        if (Gdx.input.isKeyPressed(Keys.D) && wasIceCometKeyUnpressed && playerMana >= 15) { // Fireball spell used if 's' key pressed and key was not pressed in the last frame
		        	int mouseX = Gdx.input.getX();
		    		int mouseY = Gdx.input.getY();
		        	Spell iceComet = new Spell ("icecomet", iceballSpd, iceballDmg, iceballImg, iceballInitX, iceballInitY, iceballSpriteWidth, iceballSpriteHeight, mouseX, mouseY);
		        	spellList.addSpell(iceComet);
		        	wasIceCometKeyUnpressed = false;
		        	playerMana-=15;
		        }
		        if (!(Gdx.input.isKeyPressed(Keys.D))){
		        	wasIceCometKeyUnpressed = true;
		        }
		        
		        // -------------------------------------------------------
		        // This section of code checks if the "w" key is pressed, for the voidGate spell
		        
		        if (Gdx.input.isKeyPressed(Keys.W) && wasvoidGateKeyUnpressed && playerMana >= 10) { // Fireball spell used if 's' key pressed and key was not pressed in the last frame
		        	int mouseX = Gdx.input.getX();
		    		int mouseY = Gdx.input.getY();
		        	Spell voidGate = new Spell ("voidGate", voidGateSpd, voidGateDmg, voidGateImg, voidGateInitX, voidGateInitY, voidGateSpriteWidth, voidGateSpriteHeight, mouseX, mouseY);
		        	spellList.addSpell(voidGate);
		        	wasvoidGateKeyUnpressed = false;
		        	playerMana-=10;
		        }
		        if (!(Gdx.input.isKeyPressed(Keys.W))){
		        	wasvoidGateKeyUnpressed = true;
		        }
		        
		        // -------------------------------------------------------
		        // Checks if player presses 'f', for ultimate fire wave ability
		        
		        if (Gdx.input.isKeyPressed(Keys.F) && wasFireWaveKeyUnpressed && playerUltMana >= 850) { // FireWave spell used if 'd' key pressed and key was not pressed in the last frame
		        	int mouseX = Gdx.input.getX();
		    		int mouseY = Gdx.input.getY();
		        	Spell firewave = new Spell ("firewave", fireWaveSpeed, fireWaveDmg, fireWaveImg, fireWaveInitX, fireWaveInitY, fireWaveSpriteWidth, fireWaveSpriteHeight, mouseX, mouseY); // Spell deals 0.5s per frame that the mob and spell overlap for
		        	spellList.addSpell(firewave);
		        	wasFireWaveKeyUnpressed = false;
		        	playerUltMana-=850;
		        }
		        if (!(Gdx.input.isKeyPressed(Keys.F))){
		        	wasFireWaveKeyUnpressed = true;
		        }
		        
		        // -------------------------------------------------------
		
		        
		        batch.begin();
		        // Begin by drawing static images
		        batch.draw( backgroundImage, 0, -20, 645, 500 );
		        batch.draw( wizardImage, wizardX, wizardY, 50, 60 ); 
		        batch.draw( playerHealthImg, 180, 5, 80, 25 ); 
		                
		        // The following code transitions the mobs in the game, moving them frame by frame
		        if (frameSkipper == 1){ // Enemies were too fast, so by only moving every other frame enemy speeds are effectively halved
		        	for(int i = 0; i < enemyList.enemiesArrayListSize(); i++){
		        	
		        		Enemy en = enemyList.getEnemy(i);
		        	
		        		batch.draw(en.getEnemySprite(), en.getCurrentX(), en.getCurrentY()-en.getEnemySpeed(), en.getWidth(), en.getHeight()); // Every enemy object has a speed. Use that to move the objects.
		        		en.Move(en.getCurrentX(), en.getCurrentY()-en.getEnemySpeed()); // The enemies are only moved every other frame
		        		double enHP = en.getEnemyHealth(); 
		        		int enHPAsInt = (int) enHP;
		        		batch.draw(enemyHealthBar, en.getCurrentX()+en.positioner(en.getName()), en.getCurrentY()-10, enHPAsInt*en.multiplier(en.getName()), HPBarHeight);
		        	}
		        	frameSkipper--;
		        }
		        
		        else{
		        	for(int i = 0; i < enemyList.enemiesArrayListSize(); i++){ // Every other frame, the enemy is redrawn at it's previous position
		            	
		        		Enemy en = enemyList.getEnemy(i);
		        	
		        		batch.draw(en.getEnemySprite(), en.getCurrentX(), en.getCurrentY(), en.getWidth(), en.getHeight());
		        		double enHP = en.getEnemyHealth(); 
		        		int enHPAsInt = (int) enHP;
		        		batch.draw(enemyHealthBar, en.getCurrentX()+en.positioner(en.getName()), en.getCurrentY()-10, enHPAsInt*en.multiplier(en.getName()), HPBarHeight);
		        		
		        	}
		        	frameSkipper++;
		        }
		        // -------------------------------------------------------
		        // The following code moves spells, frame by frame
		        
		        for(int z = 0; z < spellList.spellsArrayListSize(); z++){
		        	
		        	Spell sp = spellList.getSpell(z);
		        	String spellName = sp.getSpellName();
		        	
		        	if (spellName == "fireWhip"){
		        	
				    	int xPosOrNeg = 0;
				    	int yPosOrNeg = 0;
				    	        	
				    	if (sp.getSpellXPos() < sp.getSpellXDes()){
				    		xPosOrNeg = sp.getSpellSpeed();
				    	}
				    	else if (sp.getSpellXPos() > sp.getSpellXDes()){
				    		xPosOrNeg = (sp.getSpellSpeed())*(-1);
				    	}
				    	
				    	if (sp.getSpellYPos() < sp.getSpellYDes()){
				    		yPosOrNeg = (sp.getSpellSpeed());
				    	}
				    	else if (sp.getSpellYPos() > sp.getSpellYDes()){
				    		yPosOrNeg = (sp.getSpellSpeed());
				    	}
				    	// The code below deletes any spell sprites that are not moving by storing the old and new positions and comparing them
				    	float oldX = sp.getSpellXPos();
				    	float oldXPlus = oldX++;
				    	float oldY = sp.getSpellYPos();
				    	float oldYPlus = oldY++;
				    	
				    	sp.setPosition(sp.getSpellXPos()+xPosOrNeg, sp.getSpellYPos()+yPosOrNeg);
				    	batch.draw(sp.getSpellSprite(), sp.getSpellXPos(), sp.getSpellYPos(), sp.getSpellWidth(), sp.getSpellHeight());
				    
				    	if ((oldX == sp.getSpellXPos() || oldXPlus == sp.getSpellXPos()) && (oldY == sp.getSpellYPos()) || oldYPlus == sp.getSpellYPos()){
				    		spellList.removeSpell(z); // If a spell object is stuck (hasn't moved between frames) it gets deleted. This was previously a problem.
				    	}
				    	
		        	}
		        	
		        	else if (spellName == "fireball" || spellName == "icecomet" || spellName == "voidGate"){
		        		int xSpawn = sp.getSpellXDes();
				    	sp.setPosition(xSpawn, sp.getSpellYPos()+sp.getSpellSpeed());
				    	batch.draw(sp.getSpellSprite(), sp.getSpellXPos()-25, sp.getSpellYPos(), sp.getSpellWidth(), sp.getSpellHeight());
		        	}
		        	
		        	else if (spellName == "firewave"){
		        		int xCentre = -13;
		        		sp.setPosition(xCentre, sp.getSpellYPos()+sp.getSpellSpeed());
				    	batch.draw(sp.getSpellSprite(), sp.getSpellXPos(), sp.getSpellYPos(), sp.getSpellWidth(), sp.getSpellHeight());
		        	}
		
		        	if (sp.getSpellYPos() > 500){
			    		spellList.removeSpell(z);
			    	}
		        	
		        }
		        
		        // -------------------------------------------------------
		        
		        batch.draw( fireWhipCDBarOutline, 0, 0, 165, 15 );
		        batch.draw( fireWhipCooldownBar, 7, 5, fireWhipCooldown, 5 );
		        
				batch.draw(manaBarOutline, 0, 20, 109, 11);
				batch.draw(manaBar, 4, 23, playerMana, 5);
				
				int scaledUltMana = (playerUltMana/10);
				batch.draw(ultBarOutline, 0, 40, 107, 9);
				batch.draw(ultBar, 3, 42, scaledUltMana, 5);
		
		        batch.end();
		        checkCollisions();
		        
		        if (playerMana < 100 && frameSkipper == 1){ // Increases the player's mana by 1 every frame, max 100 mana.
		        	playerMana++;
		        	
		        }
		        
		        if (playerUltMana < 1000 && frameSkipper == 1){ // Increases the player's mana by 1 every frame, max 100 mana.
		        	playerUltMana++;
		        }
	    	}
	    }
    }
    
    public void spawner(String enemyName){ // Spawns a mob based on the random number generator 
    	int spawnY = 520;
    	
    	int spawnX = ranNum.nextInt(225) - 7; // Randomly decides the initial 'x' position of the enemy. 'y' position is pre-set.
    	
    	if (enemyName == "ghost"){ // Different types of mobs, with different stats (health, speed, etc).
    		Enemy ghost = new Enemy("ghost", 6, 3, ghostImage, spawnX, spawnY, 25, 25, enemyHealthBar);
    		enemyList.addEnemy(ghost);
    	}
    	
    	else if (enemyName == "wolf"){
    		Enemy wolf = new Enemy("wolf", 5, 10, wolfImage, spawnX, spawnY, 50, 36, enemyHealthBar);
    		enemyList.addEnemy(wolf);
    	}
    	
    	else if (enemyName == "goblin"){
    		Enemy goblin = new Enemy("goblin", 4,20, goblinImage, spawnX, spawnY, 57, 50, enemyHealthBar);
    		enemyList.addEnemy(goblin);
    	}
    	
    	else if (enemyName == "scarecrow"){
    		Enemy scarecrow = new Enemy("scarecrow", 3,35, scarecrowImage, spawnX, spawnY, 50, 50, enemyHealthBar);
    		enemyList.addEnemy(scarecrow); 
    	}
    	
    	else if (enemyName == "darknessKnight"){
    		Enemy darkKnight = new Enemy("dkKnight", 2,50, darknessKnightImage, spawnX, spawnY, 70, 58, enemyHealthBar);
    		enemyList.addEnemy(darkKnight);
    	}
    	
    	else if (enemyName == "deathEater"){
    		Enemy deathEater = new Enemy("deathEater", 1,100, deathEaterImage, spawnX, spawnY, 130, 75, enemyHealthBar);
    		enemyList.addEnemy(deathEater);
        	spookySound.play(1.0f); // Play this sound effect when the DeathEater is spawned
    	}
    }
    
    public void checkCollisions(){ // Check object collisions
    	for (int r = 0; r < enemyList.enemiesArrayListSize(); r++){ // Iterate through every object in arrayList containing Enemy objects
    		Enemy en = enemyList.getEnemy(r);
    		Rectangle enemyRectangle = en.getRectangle(en.getName());
    		
    		if (en.getCurrentY() < -30 && en.getName() != "ghost"){ // If ghost makes it past, health is not lost. Else, 1 heart is lost and player health bar is updated to reflect that
    			playerHP--; 
    			if (playerHP == 5){
    				playerHealthImg = new Sprite( new Texture(Gdx.files.internal("assets/5HP.png")) );
    			}
    			else if (playerHP == 4){
    				playerHealthImg = new Sprite( new Texture(Gdx.files.internal("assets/4HP.png")) );
    			}
    			else if (playerHP == 3){
    				playerHealthImg = new Sprite( new Texture(Gdx.files.internal("assets/3HP.png")) );
    			}
    			else if (playerHP == 2){
    				playerHealthImg = new Sprite( new Texture(Gdx.files.internal("assets/2HP.png")) );
    			}
    			else if (playerHP == 1){
    				playerHealthImg = new Sprite( new Texture(Gdx.files.internal("assets/1HP.png")) );
    			}
    			else if (playerHP == 0){
    				playerHealthImg = new Sprite( new Texture(Gdx.files.internal("assets/0HP.png")) );
    				firstRun = true;
    				gameOver = true;
    			}
    			enemyList.enemyDead(r); // Enemy only takes 1 heart
    		}
    		
    		for (int f = 0; f < spellList.spellsArrayListSize(); f++){ // Iterate through arrayList of all Spell objects
    			Spell sp = spellList.getSpell(f);
    			Rectangle spellRectangle = sp.getRectangle();
    			double spellDamage = sp.getSpellDamage();
    			
    			if (spellRectangle.overlaps(enemyRectangle)){ // If the objects (enemy & spell objects) overlap
    				en.reduceEnemyHP(spellDamage);
    				double enemyHPRemaining = en.getEnemyHealth();
    				
    				if (enemyHPRemaining <= 0){ // Check if enemy is dead
    					enemyList.enemyDead(r);
    					break;
    				}
    				else if (enemyHPRemaining > 0 && (!(sp.getSpellName() == "firewave")) && (!(sp.getSpellName() == "icecomet"))){ // If enemy is not dead, spell is destroyed, doing damage to only one enemy, except for firewave and icecomet spells
    					spellList.removeSpell(f);
    				}
    				
    				if (sp.getSpellName() == "icecomet"){ // If enemy hit by ice Spell, enemy is slowed based on current speed
    					int enemySpd = en.getEnemySpeed();
    					if (enemySpd >= 3){
    						en.speedDown();
    						en.speedDown();
    					}
    					else if (enemySpd >= 2){
    						en.speedDown();
    					}
    				}
    				
    				if (sp.getSpellName() == "voidGate"){ // If enemy hit by voidGate, they are teleported back in space
    					int xLoc = ranNum.nextInt(225)+1;
    					en.Move(xLoc, en.getCurrentY() + voidGatePushbackAmount);
    				}
    			}		
    		}		
    	}
    }    
}
