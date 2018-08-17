#version 400 core

in vec2 vertex;
in vec2 texturecoord;

out vec2 pass_Texturecoord;

uniform vec2 position;
uniform vec2 scale;
uniform float angle;

void main(void) {
	float rad = radians(angle);
	float cosine = cos(rad);
	float sine = sin(rad);
	float x = vertex.x * cosine - vertex.y * sine;
	float y = vertex.x * sine + vertex.y * cosine;
	vec2 rotPos = vec2(x, y);
	vec2 absPosition = rotPos * scale + position;
	gl_Position = vec4(absPosition.x * 9.0 / 16.0, absPosition.y, 0.0, 1.0);
	pass_Texturecoord = texturecoord;
}
