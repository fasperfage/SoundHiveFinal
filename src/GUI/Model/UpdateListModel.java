/*
 * Erhvervsakademi Sydvest, Computer Science 2016-2017, Carlos F. Ognissanti
 * To change this header, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Model;

import GUI.Controller.MainViewController;

/**
 * Singleton pattern (Only one object of this class at the time). It is used to
 * update automatically the list view
 *
 * @author Yuki
 */
public class UpdateListModel
{

 private MainViewController mView;
 private static final UpdateListModel INSTANCE = new UpdateListModel();

 private UpdateListModel()
 {
 }

 public static UpdateListModel getUpdateList()
 {
  return INSTANCE;
 }

 public void setMainView(MainViewController mView)
 {
  this.mView = mView;
 }

 public void updateMainList()
 {
  if (mView != null)
  {
   mView.updateList();
  }
 }
}
