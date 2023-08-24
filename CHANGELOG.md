# CHANGELOG

## v1.2.1

### Updated Dependencies

- `Forgelin-Continuous`
  - Revived to match the version of the kotlin library
- `Modular UI`

### Forgelin Bridge

- This function is to disguise Forgelin Continuous as Shadowfact's Forgelin. Therefore, other mods that requires
  Shadowfact's Forgelin can use Forgelin Continuous as an alternative.

### Blocks

- [WIP] `Rock Generator`
- [WIP] `Scaffolding`

### Fluids

- Revived Material Fluids
  - Only fluids that are not registered by other mods are implemented.

### Materials

- Added new materials
  - `Tungsten Steel`
- Added new properties
  - `Hardness`: affecting processing time
- Changed translation key format
  - `material.XX` -> `hiiragi_material.XX`
    - thanks to Roseyasa!
- Material Tooltips for Fluid Containers
  - Displays material properties from the fluid contained in the fluid container. (Bucket, Tank, ...)

### Shapes

- Added new shapes
  - `FENCE`
  - `SCAFFOLDING`
  - `SHEETMETAL`
  - `SLAB`
  - `SLAB_SHEETMETAL`
  - `WIRE`
- Changed translation key format
  - `shape.XX` -> `hiiragi_shape.XX`
    - thanks to Roseyasa!

## v1.2.0

### Unified RagiChemistry into RagiMaterials

- RagiMaterials supports various features based on the material system !!

### Updated Dependencies

- Removed
  - Forgelin-Continuous
    - from this version, this mod includes kotlin library
- Added
  - Mixinbooter

### New Features

- New creative tabs: COMMON
- Removed material fluids

#### Blocks

- Crucible: melts metallic materials and casts them into components

#### Items

- Unfired Cast & Cast: used for Crucible to convert MaterialStacks into ItemStacks

#### Materials

- Added melting / boiling / sublimation point for steel-like materials
  - the value is the same as iron
- Added new object `MaterialStack`
  - consisting of `HiiragiMaterial` and its quantity (Integer)
  - Similar to `FluidStack` but more suitable for the material system
- Fixed incorrect material info for grinding balls (Ender IO)

#### Shapes

- Changed the type of `HiiragiShape.scale` from Double to Integer
  - this value is the same as fluid amount
    - Example: a scale of ingot is 144

### Integration

#### Had Enough Items

- Added new category
  - Crucible
  - Crushing (Maybe removed in next update)
  - Heat Source (Maybe removed in next update)
  - Material
- Added new ingredient: `MaterialStack`

### For Devs

- Unified some registry classes into `HiiragiRegistry`
- New Capability `IMaterialHandler`
  - Similar to `IFluidHander`
- Item Interface `ICastItem`
  - Used for Crucible Casting Recipe
- Added new function `alloyOf()` to create alloy materials
- Added core mod and mixin (current not used)
- Added new contributor: [yuzu_machan](https://github.com/yuzu-machan)

## v1.1.0

### Blocks

- Added PLACABLE blocks!!
  - when `Enable MetaTileEntity Blocks` is true
    - default: false
- Unified textures of all material storage block

### Config

- Renamed `Misc` -> `Material`

### Creative Tabs

- Added `RMCreativeTabs.MATERIAL_BLOCK`
- Renamed `RMCreativeTabs.MATERIAL` -> `RMCreativeTabs.MATERIAL_ITEM`

### Developing Environment

- Migrated to [GregTechCEu/BuildScripts](https://github.com/GregTechCEu/Buildscripts)

### Material

- Added `MaterialRegistryEvent` for registering HiiragiMaterial
- Fixed problem: `MaterialType.GEM_4xADVANCED` not including `gem`
- Remove duplicated shapes from vanilla materials
  - When `Disable Vanilla Parts` is true

### Shape

- Added `ShapeReistryEvent` for registering HiiragiShape
- Moved HiiragiShape fields from `ShapeRegistry` to `HiiragiShapes`

## v1.0.0

### The first official release!