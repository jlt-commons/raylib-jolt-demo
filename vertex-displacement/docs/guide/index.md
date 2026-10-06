# vertex-displacement

a flat plane made terrain in the vertex stage

Category: shaders

![vertex-displacement](../demos/vertex-displacement.gif)

## Run it

```sh
cd vertex-displacement && bb run   # from this demo (or jolt run, jolt -M:run)
bb vertex-displacement             # from the repo root (or jolt -M:vertex-displacement)
```

## About

raylib [shaders] example - vertex displacement (`jolt -M:vertex-displacement`).

Port of raylib's examples/shaders/shaders_vertex_displacement.c. A flat
subdivided plane is pushed into rolling terrain entirely on the GPU: the mesh
never changes and neither does anything on the CPU side of the frame.

The vertex stage samples a Perlin noise texture and adds the red channel to
each vertex's y. Because the sample coordinate is itself animated by time,
the same static mesh and the same static texture produce moving terrain. The
displaced height is then passed to the fragment stage, which uses it for
nothing but colour, so peaks come out pale and troughs dark.

The subdivision is what makes it work. GenMeshPlane's resX and resZ decide
how many vertices there are to displace, and a plane with four is still a
plane however good the shader is, because displacement only ever moves
vertices that exist.

Sampling a texture from the vertex stage is the one piece of plumbing worth
noting, and the obvious call is the wrong one. rl/set-uniform-texture! goes
through SetShaderValueTexture, which raylib applies through its own render
batch, and DrawMesh draws outside that batch. The sampler then reads zeroes,
the displacement comes out as nothing, and the plane stays flat with no error
anywhere. rl/bind-sampler! sets the GL state directly instead, once, the way
raylib own example does. Slot 0 belongs to the material diffuse map.

raylib ships the noise as GenImagePerlinNoise and the shaders as two files.
The noise is generated here the same way; the shaders are inline strings.
