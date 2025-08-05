package pages;

import utils.ElementActions;

public abstract class BasePage {

  protected static final ElementActions actions = new ElementActions();

  public abstract boolean isPageLoaded();
}