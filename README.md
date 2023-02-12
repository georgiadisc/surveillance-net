# surveillance-net

A Java Swing Application for a hypothetical surveillance network with a micro framework inspired by Flutter Framework.

## 🏛️ Architecture

### `/models`

This is where the business logic of the application lies.

### `/pages`

The pages of the application, **HomePage**, **SuspectPage** and **NetworkPage**. Pages get their own id and can later be added to the Router.

### `/utility`

Utility classes like **Router** and **Database** alongside other helper components for Java Swing, like **TextPrompt** and **SizedBox**.

### `/widgets`

The widgets of the application live here. Widgets are reusable blocks of code that get access to the database and the mechanism for building and updating their content. Widgets can also be embedded into other widgets or pages.

## 📸 Screenshots

<table>
  <tr>
    <td>Home Page</td>
     <td>Suspect Page</td>
     <td>Network Page</td>
  </tr>
  <tr>
    <td><img src="images/home_page.png" alt="Home Page" title="Home Page" width=300></td>
    <td><img src="images/suspect_page.png" alt="Suspect Page" title="Suspect Page" width=300></td>
    <td><img src="images/network_page.png" alt="Network Page" title="Network Page" width=300></td>
  </tr>
 </table>
