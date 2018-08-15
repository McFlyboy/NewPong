#version 400 core

in vec2 vertex;
in vec2 texturecoord;

out vec2 pass_Texturecoord;

uniform vec2 position;

void main(void) {
	vec2 worldPosition = position + vertex;
	gl_Position = vec4(worldPosition.x * 9.0 / 16.0, worldPosition.y, 0.0, 1.0);
	pass_Texturecoord = texturecoord;
}
