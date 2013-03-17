package notifier;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

var img = Image {
            url: "{__DIR__}figure2.png"
          }

Stage {
    width: 200
    height: 250
    title: "Notificacion de correo"
    scene: Scene {
        content: [

            ImageView {
                x: 0
                y: 0
                image: img
            }

            Text {
                font: Font {
                    size: 16
                }
                x: 10
                y: 140
                content: "Nuevo correo recivido!!!..."
            }
        ]
    }
}
