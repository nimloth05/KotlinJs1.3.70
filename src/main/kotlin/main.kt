import ch.viseon.threejs.declarations.cameras.PerspectiveCamera
import ch.viseon.threejs.declarations.geometries.BoxGeometry
import ch.viseon.threejs.declarations.materials.MeshNormalMaterial
import ch.viseon.threejs.declarations.objects.Mesh
import ch.viseon.threejs.declarations.renderers.WebGLRenderer
import ch.viseon.threejs.declarations.scenes.Scene
import kotlin.browser.document
import kotlin.browser.window
import kotlin.js.json


fun main() {
    js("require('style.css');")

    window.onload = {
        val camera = PerspectiveCamera(70.0, (window.innerWidth / window.innerHeight).toDouble(), 0.01, 10.0)
        camera.position.z = 1.0

        val scene = Scene();

        val geometry = BoxGeometry(0.2, 0.2, 0.2);
        val material = MeshNormalMaterial();

        val mesh = Mesh(geometry, material);
        scene.add(mesh);

        val renderer = WebGLRenderer()
        renderer.setSize(window.innerWidth, window.innerHeight);
        document.body?.appendChild(renderer.domElement);
        render(mesh, scene, camera, renderer)
    }

}

fun render(mesh: Mesh, scene: Scene, camera: PerspectiveCamera, renderer: WebGLRenderer) {

    fun animate() {

        window.requestAnimationFrame {
            animate()
        }

        mesh.rotation.x += 0.01;
        mesh.rotation.y += 0.02;

        renderer.render(scene, camera);
    }

    animate()
}
