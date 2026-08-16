---
name: coffeeshop-design-system
description: >
  Design system rules and component standards for the Coffee Shop Android app.
  Use this skill whenever generating, editing, or reviewing ANY Jetpack Compose UI code
  in this project — including screens, components, previews, theme files, spacing,
  colour usage, typography, shimmer/loading states, or anything visual.
  Trigger on phrases like: "create a screen", "add a component", "build the UI",
  "make a card", "add a button", "loading state", "empty state", "theme", "colour",
  "typography", "spacing", "design", "composable", "preview", or any request that
  will result in Compose code being written. If in doubt, always read this skill first.
  Never generate Compose UI code without consulting this skill.
---

# Coffee Shop Design System — AI Skill

> Read `references/DESIGN_SYSTEM.md` for full token values, component contracts,
> decision table, and shimmer patterns before writing any code.
> This SKILL.md is the trigger + rules summary. The reference file has all the details.

---

## Quick Rules (memorise these — apply to every file you generate)

1. **No hardcoded values — ever.**
   Never write `Color(0xFF...)`, `16.dp`, `"Poppins"`, or `FontWeight.Bold` inline
   in a composable. Always use `MaterialTheme.colorScheme.*`, `MaterialTheme.spacing.*`,
   `MaterialTheme.typography.*`, or `MaterialTheme.shapes.*`.

2. **Components are dumb.**
   No `hiltViewModel()`, no `NavController`, no repository calls inside `core-ui`
   composables. Every component takes plain data + lambda callbacks only.

3. **Every component needs a `@Preview`.**
   Always add both light and dark `@Preview` annotations. Use `PreviewWrapper` from
   `core/core-ui/.../preview/PreviewWrapper.kt` as the wrapper.

4. **Shimmer over spinner.**
   Any screen with a loading state must use `ShimmerCard` or `ShimmerBox` from
   `core-ui`. Never use `CircularProgressIndicator` as a full-screen loader.
   Only use it for inline/button loading states.

5. **4dp spacing grid.**
   All padding/spacing values must come from `MaterialTheme.spacing.*` which maps to:
   xxs=2dp, xs=4dp, sm=8dp, md=12dp, lg=16dp, xl=24dp, xxl=32dp, xxxl=48dp.
   No arbitrary dp values.

6. **Decision: core-ui vs feature module.**
   - Used on 2+ screens → `core/core-ui/components/`
   - Specific to 1 feature → `feature-x/.../presentation/components/`
   - Read the decision table in `references/DESIGN_SYSTEM.md` Section 6 for edge cases.

7. **`core-ui` imports nothing from features or navigation.**
   `core-ui` sits at the bottom of the dependency graph.
   It must NEVER import `:core:core-navigation`, `:feature:*`, or `:app`.

8. **Modifier as last parameter.**
   Every composable must accept `modifier: Modifier = Modifier` as its last parameter
   before lambdas. Pass it to the root layout element.

9. **State hoisting.**
   Stateful logic lives in ViewModel. Composables receive state and emit events upward.
   Never use `remember { mutableStateOf(...) }` for business data inside a component.
   Only use it for pure UI state (e.g. expanded/collapsed, ripple).

10. **Accessibility.**
    Every interactive element needs a `contentDescription`.
    Every image needs `contentDescription` (or explicit `null` if decorative).

---

## When Generating a New Component — Checklist

Before writing code, answer these:
- [ ] Is this used on more than one screen? → `core-ui`. Otherwise → feature module.
- [ ] Does it need ViewModel or nav? → wrong layer, move logic up.
- [ ] Does it have a loading skeleton counterpart? → create `XxxSkeleton` alongside it.
- [ ] Does it have an empty state? → create `XxxEmptyState` alongside it.
- [ ] Have I added `@Preview` (light + dark)? → mandatory.
- [ ] Have I used only theme tokens for colours/spacing/type? → check every line.

---

## When Generating a New Screen — Checklist

- [ ] ViewModel exposes `StateFlow<XxxUiState>` — collected via `collectAsStateWithLifecycle()`
- [ ] ViewModel exposes `SharedFlow<XxxUiEvent>` for one-shot events (navigation, snackbar)
- [ ] Loading state → shimmer skeleton (NOT spinner)
- [ ] Error state → `ErrorView` composable from `core-ui`
- [ ] Empty state → feature-specific empty composable or `core-ui` generic
- [ ] Screen composable accepts nav callbacks as lambdas (no NavController parameter)
- [ ] `@Preview` for each major state: Loading, Success, Error, Empty

---

## File Locations to Generate Into

| What you're creating | Path |
|---|---|
| Colour/spacing/type tokens | `core/core-ui/src/main/java/com/coffeeshop/core/ui/tokens/` |
| Theme wiring | `core/core-ui/src/main/java/com/coffeeshop/core/ui/theme/` |
| Shared components | `core/core-ui/src/main/java/com/coffeeshop/core/ui/components/<category>/` |
| Shimmer/skeleton | `core/core-ui/src/main/java/com/coffeeshop/core/ui/components/loader/` |
| Preview wrapper | `core/core-ui/src/main/java/com/coffeeshop/core/ui/preview/` |
| Feature-specific component | `feature/feature-x/src/main/java/com/coffeeshop/feature/x/presentation/components/` |
| Screen composable | `feature/feature-x/src/main/java/com/coffeeshop/feature/x/presentation/XxxScreen.kt` |

---

## Reference File

→ Read `references/DESIGN_SYSTEM.md` now for:
- Full token values (all colours, spacing grid, type scale, shapes, elevation, motion)
- Complete component catalogue with exact function signatures
- Shimmer/skeleton implementation (copy-paste ready)
- The full decision table (core-ui vs feature)
- `PreviewWrapper` implementation
- `build.gradle.kts` for `core-ui`
